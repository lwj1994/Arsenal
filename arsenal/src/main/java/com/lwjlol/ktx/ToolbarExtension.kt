package com.lwjlol.ktx

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar


/**
 * 初始化 [Toolbar]
 * @param onClickNav 点击返回键
 */
fun Toolbar.init(
    title: String,
    @MenuRes menuId: Int = 0,
    @DrawableRes navIcon: Int = 0,
    @Px height: Int = 0,
    onClickNav: View.OnClickListener? = null
) {
    if (menuId != 0) {
        inflateMenu(menuId)
    }
    if (title.isNotEmpty()) {
        this.title = title
    }
    if (height != 0) {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    }
    if (navIcon != 0) {
        setNavigationIcon(navIcon)
    }
    onClickNav?.let { setNavigationOnClickListener(it) }
}

fun Toolbar.init(
    @StringRes title: Int,
    @MenuRes menuId: Int = 0,
    @DrawableRes navIcon: Int = 0,
    onClickNav: View.OnClickListener? = null
) {
    if (menuId != 0) {
        inflateMenu(menuId)
    }
    if (context.getString(title).isNotEmpty()) {
        this.title = context.getString(title)
    }
    if (navIcon != 0) {
        setNavigationIcon(navIcon)
    }
    onClickNav?.let { setNavigationOnClickListener(it) }
}
