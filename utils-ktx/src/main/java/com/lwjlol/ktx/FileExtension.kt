package com.lwjlol.ktx

import java.io.File

/**
 * 计算某文件的大小，建议在 io 线程计算
 */
val File.size: Long
    get() = computeSize(this)


private fun computeSize(dir: File): Long {//计算文件夹大小方法
    var length = 0L
    dir.listFiles()?.forEach { file ->
        length += if (file.isFile) {
            file.length();
        } else {
            computeSize(file);//如果是文件夹,则递归调用Length方法
        }
    }
    return length
}


/**
 * 删除某个文件夹及其子文件夹
 * @return 是否删除成功
 */
fun File?.deleteDir(): Boolean {
    if (this != null && this.isDirectory) {
        val children = this.list() ?: return false
        children.forEach { s ->
            val success = File(this, s).deleteDir()
            if (!success) {
                return false
            }
        }
        return this.delete()
    } else if (this != null && this.isFile) {
        return this.delete()
    } else {
        return false
    }
}


/**
 * 截取某个文件路径的名字
 */
val String.fileName: String
    get() {
        return try {
            if (isEmpty()) return ""
            if (!contains("/")) return this
            val lastIndex = lastIndexOf("/")
            if (lastIndex + 1 > length - 1) {
                return this
            }
            return substring(lastIndex + 1)

        } catch (e: Exception) {
            this
        }
    }