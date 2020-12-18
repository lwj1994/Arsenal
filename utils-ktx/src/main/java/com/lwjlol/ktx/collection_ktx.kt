package com.lwjlol.ktx


/*集合相关*/

/**
 * 找到满足条件的索引位置
 * @param predicate 比较条件
 * @return -1 没找到
 */
inline fun <T> Iterable<T>.findIndex(predicate: (T) -> Boolean): Int {
    forEachIndexed { index, t ->
        if (predicate(t)) {
            return index
        }
    }
    return -1
}

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

fun <T : Any> List<T>.modify(
    predicate: (T) -> Boolean,
    block: (T) -> T
): List<T> {
    val mutableList = toMutableList()
    val item = mutableList.findIndex {
        predicate(it)
    }
    if (item != -1) {
        val origin = mutableList[item]
        mutableList.removeAt(item)
        mutableList.add(item, block(origin))
    }

    return mutableList
}

/**
 * 删除满足条件的所有元素
 */
fun <T : Any> List<T>.delete(predicate: (T) -> Boolean): List<T> {
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
 * 找到满足条件的索引位置
 * @param predicate 比较条件
 * @return -1 没找到
 */
inline fun <T> Array<T>.findIndex(predicate: (T) -> Boolean): Int {
    forEachIndexed { index, t ->
        if (predicate(t)) {
            return index
        }
    }
    return -1
}

