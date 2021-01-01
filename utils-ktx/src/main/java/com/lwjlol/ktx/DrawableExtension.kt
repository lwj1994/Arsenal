package com.lwjlol.ktx

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


// 获取 drawable
val Int.drawable: Drawable
    get() = ContextCompat.getDrawable(ktxContext, this)?:throw IllegalArgumentException("")



@Suppress("DEPRECATION")
fun Drawable.setColorFilterCompact(@ColorInt color: Int = Color.LTGRAY) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(color, BlendMode.SRC_IN)
    } else {
        setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}