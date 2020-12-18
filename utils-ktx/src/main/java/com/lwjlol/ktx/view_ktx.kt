package com.lwjlol.ktx

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.forEach

fun View.clearAllListener() {
    when (this) {
      is Spinner -> {
        this.onItemSelectedListener = null
      }
      is AdapterView<*> -> {
        this.onItemClickListener = null
      }
        else -> {
            this.setOnClickListener(null)
        }
    }
    this.setOnTouchListener(null)
    this.setOnDragListener(null)
    if (this is ViewGroup) {
        forEach {
            it.clearAllListener()
        }
    }
}

fun View.click(
  time: Long = 200,
  runnable: () -> Unit
) {
    setOnClickListener {
        isEnabled = false
        runnable()
        postDelayed({ isEnabled = true }, time)
    }
}

fun View.createOnClickListener(
  time: Long = 200,
  runnable: (view: View?) -> Unit
) =
    View.OnClickListener {
        it?.isEnabled = false
        runnable(it)
        postDelayed({ it?.isEnabled = true }, time)
    }

fun buildRoundedCornerDrawable(
  @ColorInt color: Int,
  radius: Float = 0F,
  tl: Float = 0F,
  tr: Float = 0F,
  bl: Float = 0F,
  br: Float = 0F
): Drawable {
    val gradientDrawable = GradientDrawable()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        gradientDrawable.setColor(color)
    } else {
        (gradientDrawable.mutate() as GradientDrawable).colors = intArrayOf(color, color)
    }
    if (radius != 0F) {
        gradientDrawable.cornerRadius = radius
    } else {
        gradientDrawable.cornerRadii = floatArrayOf(tl, tl, tr, tr, br, br, bl, bl)
    }
    return gradientDrawable
}

fun View.setRoundedBackground(
  @ColorInt color: Int = Color.WHITE,
  radius: Float = 0F,
  tl: Float = 0F,
  tr: Float = 0F,
  bl: Float = 0F,
  br: Float = 0F
) {
    ViewCompat.setBackground(this, buildRoundedCornerDrawable(color, radius, tl, tr, bl, br))
}

fun View.setRippleBackground(
  @ColorInt color: Int = Color.WHITE,
  radius: Float = 0F,
  tl: Float = 0F,
  tr: Float = 0F,
  bl: Float = 0F,
  br: Float = 0F
) {

}

fun View.pressed(
  pressedDrawable: Drawable,
  normalDrawable: Drawable = ColorDrawable(Color.TRANSPARENT)
) {
    this.apply {
        val sd = StateListDrawable().apply {
            pressed(pressedDrawable)
            normal(normalDrawable)
        }
        background = sd
    }
}

fun StateListDrawable.pressed(
  pressedDrawable: Drawable,
  reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_pressed), pressedDrawable)
    }
    return this
}

fun StateListDrawable.selected(
  drawable: Drawable,
  reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_selected), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_selected), drawable)
    }
    return this
}

fun StateListDrawable.checked(
  drawable: Drawable,
  reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_checked), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_checked), drawable)
    }
    return this
}

fun StateListDrawable.normal(drawable: Drawable): StateListDrawable {
    addState(intArrayOf(), drawable)
    return this
}

fun View.setSelectableBackground() {
    val typeValue = TypedValue()
    context.theme
        .resolveAttribute(android.R.attr.selectableItemBackground, typeValue, true)
    val attribute = intArrayOf(android.R.attr.selectableItemBackground)
    val typedArray = context.theme.obtainStyledAttributes(typeValue.resourceId, attribute)
    background = typedArray.getDrawable(0)
}

fun View.matchMatch(): View {
    layoutParams =
        ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT
        )
    return this
}

fun createLinearLayout(
  context: Context,
  isHorizontal: Boolean = false
) =
    LinearLayout(context).apply {
        matchMatch()
        orientation = if (!isHorizontal) {
            LinearLayout.VERTICAL
        } else {
            LinearLayout.HORIZONTAL
        }
    }

fun View.match_wrap(): View {
    layoutParams =
        ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT
        )
    return this
}

fun View.wrap_wrap(): View {
    layoutParams =
        ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT
        )
    return this
}

fun View.wrap_match(): View {
    layoutParams =
        ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.MATCH_PARENT
        )
    return this
}
