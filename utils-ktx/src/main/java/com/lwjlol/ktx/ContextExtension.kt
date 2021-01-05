package com.lwjlol.ktx

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat

/**
 * 将 res 转成 int
 */
fun Context.getColorInt(@ColorRes res: Int) = ContextCompat.getColor(this, res)


fun Context.getDrawableCompact(@DrawableRes res: Int) = ContextCompat.getDrawable(this, res)


fun Context.getColorFromStyle(@StyleableRes style: Int): Int {
    val a = obtainStyledAttributes(null, intArrayOf(style))
    try {
        return a.getColor(0, Color.MAGENTA)
    } finally {
        a.recycle()
    }
}

