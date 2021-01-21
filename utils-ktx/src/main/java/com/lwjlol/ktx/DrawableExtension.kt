package com.lwjlol.ktx

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getDrawableCompact(@DrawableRes res: Int) = ContextCompat.getDrawable(this, res)

@Suppress("DEPRECATION")
fun Drawable.setColorFilterCompact(@ColorInt color: Int = Color.LTGRAY) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(color, BlendMode.SRC_IN)
    } else {
        setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}
