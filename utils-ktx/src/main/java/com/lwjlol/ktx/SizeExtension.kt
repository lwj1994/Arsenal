package com.lwjlol.ktx

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.WindowManager

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


val screenWidth: Int
    get() {
        val wm = ktxContext.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
            ?: return ktxContext.resources.displayMetrics.widthPixels
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ktxContext.display?.getRealSize(point)
        } else {
            wm.defaultDisplay.getRealSize(point)
        }
        return point.x
    }

val screenHeight: Int
    get() {
        val wm = ktxContext.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
            ?: return ktxContext.resources.displayMetrics.heightPixels
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ktxContext.display?.getRealSize(point)
        } else {
            wm.defaultDisplay.getRealSize(point)
        }
        return point.y
    }

val Int.dimenValue: Int
    get() = ktxContext.resources.getDimensionPixelOffset(this)
