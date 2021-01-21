package com.lwjlol.ktx

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager


val Context.screenWidth: Int
    get() {
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

val Context.screenHeight: Int
    get() {
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


private const val NAVIGATION = "navigationBarBackground"

// 全面屏的横条是否存在
val Activity.isNavigationBarExist: Boolean
    get() {
        val vp = window.decorView as ViewGroup
        for (i in 0 until vp.childCount) {
            vp.getChildAt(i).context.packageName
            if (vp.getChildAt(i).id != -1 && NAVIGATION == resources.getResourceEntryName(
                    vp.getChildAt(
                        i
                    ).id
                )
            ) {
                return true
            }
        }
        return false
    }


// 获取准确的状态栏高度，兼容了异形屏的安全高度
val Window.statusBarHeight: Int
    get() {
        return Resources.getSystem().getDimensionPixelSize(
            Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        ).coerceAtLeast(
            safeTopHeight
        )
    }

// 获取准确的导航栏高度，兼容了全面屏
val Activity.navigationBarHeight: Int
    get() {
        if (!isNavigationBarExist) {
            return 0
        }
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }
