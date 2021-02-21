package com.lwjlol.ktx

import org.json.JSONArray


/*集合相关*/

/**
 * 找到满足条件的索引位置
 * @param predicate 比较条件
 * @return -1 没找到
 */
inline fun <T> Iterable<T>.findFirstIndex(predicate: (T) -> Boolean): Int {
    forEachIndexed { index, t ->
        if (predicate(t)) {
            return index
        }
    }
    return -1
}

inline fun <T> Array<T>.findFirstIndex(predicate: (T) -> Boolean): Int {
    forEachIndexed { index, t ->
        if (predicate(t)) {
            return index
        }
    }
    return -1
}


/**
 * 找到迭代器中的最后一个符合条件元素的下标
 */
inline fun <T> Iterable<T>.findLastIndex(predicate: (T) -> Boolean): Int {
    var result = -1
    forEachIndexed { index, t ->
        if (predicate(t)) {
            result = index
        }
    }
    return result
}

/**
 * 找到迭代器中的最后一个符合条件元素的下标
 */
inline fun <T> Array<T>.findLastIndex(predicate: (T) -> Boolean): Int {
    var result = -1
    forEachIndexed { index, t ->
        if (predicate(t)) {
            result = index
        }
    }
    return result
}


/**
 * 交换 2 个元素的位置
 */
fun <T : Any> MutableList<T>.swap(
    position1: Int,
    position2: Int
): MutableList<T> {
    val a = get(position1)
    val b = get(position2)
    removeAt(position1)
    add(position1, b)
    removeAt(position2)
    add(position2, a)
    return this
}

/**
 * 修改所有符合条件的某个元素
 */
inline fun <T : Any> List<T>.replace(
    predicate: (T) -> Boolean,
    block: (T) -> T
): List<T> {
    val mutableList = toMutableList()

    val iterator = mutableList.listIterator()
    while (iterator.hasNext()) {
        val origin = iterator.next()
        if (predicate(origin)) {
            iterator.remove()
            iterator.add(block(origin))
        }
    }
    return mutableList
}

inline fun <T : Any> List<T>.replaceFirst(
    predicate: (T) -> Boolean,
    block: (T) -> T
): List<T> {
    val mutableList = toMutableList()

    val iterator = mutableList.listIterator()
    while (iterator.hasNext()) {
        val origin = iterator.next()
        if (predicate(origin)) {
            iterator.remove()
            iterator.add(block(origin))
            break
        }
    }
    return mutableList
}

inline fun <T : Any> List<T>.replaceLast(
    predicate: (T) -> Boolean,
    block: (T) -> T
): List<T> {
    val mutableList = toMutableList()

    val iterator = mutableList.listIterator(size)
    while (iterator.hasPrevious()) {
        val origin = iterator.previous()
        if (predicate(origin)) {
            iterator.remove()
            iterator.add(block(origin))
            break
        }
    }
    return mutableList
}

/**
 * 删除满足条件的所有元素
 */
inline fun <T : Any> Collection<T>.delete(predicate: (T) -> Boolean): List<T> {
    val list = toMutableList()
    val iterator = list.iterator()

    while (iterator.hasNext()) {
        val item = iterator.next()
        if (predicate(item)) {
            iterator.remove()
        }
    }
    return list
}

/**
 * 防止下标越界
 */
fun <T : Any> List<T>.getSafely(index: Int): T? {
    return if (isValid(index)) {
        get(index)
    } else {
        null
    }
}

/**
 * 判断数组是否越界
 */
fun <T : Any> Collection<T>.isValid(index: Int): Boolean {
    return index >= 0 && index <= size - 1
}


/**
 * 以 [delimiter] 为分割点将 String 集合 转成 String 文本，如 输入数组 [12313,123123,32131,3123]，
 * 输出 String ："12313,123123,32131,3123"
 * @param delimiter 分隔符
 */
fun Collection<String>.toSplitString(delimiter: String = ","): String {
    val sp = StringBuilder()
    forEachIndexed { i, s ->
        if (i != size - 1) {
            sp.append(s).append(delimiter)
        } else {
            sp.append(s)
        }
    }
    return sp.toString()
}

/**
 * 将一个 String 集合转成 JsonArray
 */
fun Collection<String>.toArrayJson(): String {
    if (isEmpty()) {
        return ""
    }
    val jsonArray = JSONArray()
    forEach {
        jsonArray.put(it)
    }
    return jsonArray.toString()
}
