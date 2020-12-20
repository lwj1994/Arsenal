package com.lwjlol.ktx

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


// 获取 drawable
val Int.drawable: Drawable
    get() = ContextCompat.getDrawable(ktxContext, this)?:throw IllegalArgumentException("")



