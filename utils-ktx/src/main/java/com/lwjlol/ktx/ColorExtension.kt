package com.lwjlol.ktx


import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


val Int.colorInt: Int
    @ColorInt
    get() = ContextCompat.getColor(ktxContext, this)

val String.colorInt: Int
    @ColorInt
    get() = Color.parseColor(this)

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

