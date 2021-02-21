package com.lwjlol.ktx

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.graphics.toColorInt

/**
 * @author luwenjie on 2019-11-07 14:38:50
 */

fun TextView.setLeftDrawable(@DrawableRes res: Int) {
    setCompoundDrawablesWithIntrinsicBounds(res, 0, 0, 0)
}

fun TextView.setRightDrawable(@DrawableRes res: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, res, 0)
}

fun TextView.setTopDrawable(@DrawableRes res: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, res, 0, 0)
}

fun TextView.setBottomDrawable(@DrawableRes res: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, res)
}

fun String.toSpannable(vararg span: Any) = SpannableStringBuilder(this).apply {
    span.forEach {
        setSpan(it, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

fun createClickableSpan(
    @ColorInt color: Int,
    onClick: () -> Unit
) = object : ClickableSpan() {
    override fun onClick(widget: View) {
        onClick()
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = color
        ds.isUnderlineText = false
    }
}

val boldTextSpan: StyleSpan
    get() = StyleSpan(Typeface.BOLD)
val defaultTextSpan: StyleSpan
    get() = StyleSpan(Typeface.NORMAL)
val Int.sizeSpan
    get() = AbsoluteSizeSpan(this, true)

val Int.colorSpan
    get() = ForegroundColorSpan(this)

val String.colorSpan
    get() = ForegroundColorSpan(this.toColorInt())
