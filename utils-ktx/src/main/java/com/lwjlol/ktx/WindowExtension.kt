@file:Suppress("DEPRECATION")

package com.lwjlol.ktx

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES
import android.view.*

private val sdkM = Build.VERSION.SDK_INT >= 23
private val sdkR = Build.VERSION.SDK_INT >= 30

/**
 * 状态栏沉浸模式的开关
 */
var Window.immersionMode: Boolean
    @SuppressLint("NewApi")
    set(value) {
        statusBarColor = if (value) {
            Color.TRANSPARENT
        } else {
            Color.WHITE
        }
        navigationBarColor = if (value) {
            Color.TRANSPARENT
        } else {
            Color.BLACK
        }
        if (sdkR) {
            setDecorFitsSystemWindows(!value)
        } else {
            if (value) {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility =
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE   // 防止状态栏、底部导航栏隐藏时，内容区域大小发生变化
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)  // Activity会全屏显示，但状态栏不会被隐藏，状态栏依然可见，Activity 顶端布局部分会被状态栏盖住
            } else {
                clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }
    get() {
        return if (sdkR) {
            statusBarColor == Color.TRANSPARENT
        } else {
            (decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN == View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }

/**
 * 状态栏深色内容
 */
var Window.darkStatusBarContent: Boolean
    @SuppressLint("NewApi") @TargetApi(VERSION_CODES.M)
    set(value) {
        if (!sdkM) return
        if (sdkR) {
            decorView.post {
                insetsController?.setSystemBarsAppearance(
                    if (value) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
                insetsController?.setSystemBarsAppearance(
                    if (value) WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS else 0,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            }
        } else {
            decorView.systemUiVisibility = if (value) {
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }

    }
    @TargetApi(VERSION_CODES.M)
    get() {
        return (sdkM && View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR and attributes.flags == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

/**
 * 全屏
 */
var Window.fullScreen: Boolean
    @SuppressLint("NewApi")
    set(value) {
        if (sdkR) {
            decorView.post {
                if (value) {
                    insetsController?.hide(WindowInsets.Type.statusBars())
                    insetsController?.hide(WindowInsets.Type.navigationBars())
                } else {
                    insetsController?.show(WindowInsets.Type.statusBars())
                    insetsController?.show(WindowInsets.Type.navigationBars())
                }
            }
        } else {
            if (value) {
                setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            } else {
                clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

    }
    get() = attributes.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == WindowManager.LayoutParams.FLAG_FULLSCREEN

/**
 * 是否允许截图
 */
var Window.enableRecording: Boolean
    get() = attributes.flags and WindowManager.LayoutParams.FLAG_SECURE == WindowManager.LayoutParams.FLAG_SECURE
    set(value) {
        if (!value) {
            setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        } else {
            clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }
