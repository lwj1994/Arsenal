package com.lwjlol.ktx

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.WindowManager
import androidx.annotation.DimenRes

/**
 * 尺寸相关的扩展方法
 */

val Number.sp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, toFloat(), Resources.getSystem().displayMetrics
    ).toInt()

val Number.dpF: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics
    )

val Number.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics
    ).toInt()

fun Context.dimenValue(@DimenRes id: Int) = resources.getDimensionPixelOffset(id)
