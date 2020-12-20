package com.lwjlol.ktx

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 是否包含中文
 */
fun String.isContainChinese(): Boolean {
    val p: Pattern = Pattern.compile("[\u4e00-\u9fa5]")
    val m: Matcher = p.matcher(this)
    return m.find()
}

val Int.string: String
    get() = ktxContext.getString(this)


val String.emoji: String
    get() = String(Character.toChars(Integer.parseInt(this, 16)))

