package com.lwjlol.ktx

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager


fun Context.screenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        ?: return resources.displayMetrics.widthPixels
    val point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display?.getRealSize(point)
    } else {
        wm.defaultDisplay.getRealSize(point)
    }
    return point.x
}

fun Context.screenHeight(): Int {
    val context = this
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        ?: return context.resources.displayMetrics.heightPixels
    val point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        context.display?.getRealSize(point)
    } else {
        wm.defaultDisplay.getRealSize(point)
    }
    return point.y
}

