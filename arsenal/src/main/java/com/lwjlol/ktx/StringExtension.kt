package com.lwjlol.ktx

import android.content.Context
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

/**
 * 使用 [resToColorInt]  直接使用 Application Context [ktxContext] 会导致引用不到 value-night 下的颜色
 */
@Deprecated("use toString()", replaceWith = ReplaceWith(""))
val Int.string: String
    get() = ktxContext.getString(this)


val String.emoji: String
    get() = String(Character.toChars(Integer.parseInt(this, 16)))

