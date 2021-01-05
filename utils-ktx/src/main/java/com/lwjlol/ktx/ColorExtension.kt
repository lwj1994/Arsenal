package com.lwjlol.ktx


import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


/**
 * 使用 [resToColorInt]  直接使用 Application Context [ktxContext] 会导致引用不到 value-night 下的颜色
 */
@Deprecated("", ReplaceWith(""))
val Int.colorInt: Int
    @ColorInt
    get() = ContextCompat.getColor(ktxContext, this)

val String.colorInt: Int
    @ColorInt
    get() = Color.parseColor(this)


@Deprecated("")
val Int.colorAttrToInt: Int
    @ColorInt
    get() {
        val a = ktxContext.obtainStyledAttributes(null, intArrayOf(this))
        try {
            return a.getColor(0, Color.MAGENTA)
        } finally {
            a.recycle()
        }
    }

