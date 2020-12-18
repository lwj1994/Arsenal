package com.lwjlol.ktx

import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.Px
import androidx.appcompat.widget.Toolbar


/**
 * 初始化 [Toolbar]
 * @param onClickNav 点击返回键
 */
fun Toolbar.init(
    title: String,
    @MenuRes
    menuId: Int = 0,
    @DrawableRes
    navIcon: Int = 0,
    @Px
    height: Int,
    onClickNav: View.OnClickListener
) {
    if (menuId != 0) {
        inflateMenu(menuId)
    }
    if (title.isNotEmpty()) {
        this.title = title
    }
    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, height)
    setNavigationIcon(navIcon)
    setNavigationOnClickListener(onClickNav)
}