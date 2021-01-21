package com.lwjlol.ktx


import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat

val String.colorInt: Int
    @ColorInt
    get() = Color.parseColor(this)


/**
 * 将 res 转成 int
 */
fun Context.getColorInt(@ColorRes res: Int) = ContextCompat.getColor(this, res)


fun Context.getColorFromStyle(@StyleableRes style: Int): Int {
    val a = obtainStyledAttributes(null, intArrayOf(style))
    try {
        return a.getColor(0, Color.MAGENTA)
    } finally {
        a.recycle()
    }
}


