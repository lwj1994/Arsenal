package com.lwjlol.ktx

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.smoothScrollToAtOnce(position: Int) {
    layoutManager?.startSmoothScroll(
        OneTimeSmoothScroller(context).apply {
            targetPosition = position
        })
}

class OneTimeSmoothScroller(context: Context) : LinearSmoothScroller(context) {
    private var isScrolled: Boolean = false
    override fun updateActionForInterimTarget(action: Action) {
        if (isScrolled) {
            action.jumpTo(targetPosition)
        } else {
            super.updateActionForInterimTarget(action)
            action.interpolator = AccelerateInterpolator(1.5F)
            isScrolled = true
        }
    }

    override fun getVerticalSnapPreference(): Int {
        return LinearSmoothScroller.SNAP_TO_START
    }

    companion object {
        private const val TAG = "OneTimeSmoothScroller"
    }
}


/**
 * 为 [RecyclerView] 设置 padding
 * @param block:(position: 位置, viewType: item 的类型, outRect: Rect 绘制的范围, size: 布局内总的个数)
 */
inline fun RecyclerView.setItemPadding(crossinline block: (position: Int, viewType: Int, outRect: Rect, size: Int) -> Unit) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            if (position == -1) return
            val viewType = parent.layoutManager?.getItemViewType(view) ?: -1
            val itemCount = parent.layoutManager?.itemCount ?: 0
            if (itemCount == 0) return
            block(position, viewType, outRect, itemCount)
        }
    })
}


/**
 * 给 n*m 规则的表格布局添加 上下左右和内部的间距
 * @param hPadding 横向的 item 内部之间的间距
 * @param vPadding 纵向的 item 内部之间的间距
 * @param leftPadding
 * @param rightPadding
 * @param topPadding
 * @param bottomPadding
 */
fun RecyclerView.addGridItemPadding(
    hPadding: Int,
    vPadding: Int,
    leftPadding: Int,
    rightPadding: Int,
    topPadding: Int,
    bottomPadding: Int
) {
    val halfHPadding = hPadding / 2
    val halfVPadding = vPadding / 2
    setPadding(
        leftPadding - halfHPadding,
        topPadding - halfVPadding,
        rightPadding - halfHPadding,
        bottomPadding - halfVPadding
    )
    clipToPadding = false
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val manager = layoutManager as? GridLayoutManager ?: return
            val p = manager.getPosition(view)
            if (p == RecyclerView.NO_POSITION) return
            val spanCount: Int = manager.spanCount
            val itemSpanSize = manager.spanSizeLookup.getSpanSize(p)
            if (itemSpanSize != 1) {
                return
            }
            val spanIndex = manager.spanSizeLookup.getSpanIndex(p, spanCount)
            val spanGroupIndex = manager.spanSizeLookup.getSpanGroupIndex(p, spanCount)
            when {
                // 第一列
                spanIndex == 0 -> {
                    when (spanGroupIndex) {
                        // 左上
                        0 -> {
                            outRect.set(halfHPadding, halfVPadding, halfHPadding, halfVPadding)
                        }
                        else -> {
                            outRect.set(halfHPadding, halfVPadding, halfHPadding, halfVPadding)
                        }
                    }
                }
                // 最后一列
                spanIndex == spanCount - 1 -> {
                    when (spanGroupIndex) {
                        // 右上角
                        0 -> {
                            outRect.set(halfHPadding, halfVPadding, halfHPadding, halfVPadding)
                        }
                        else -> {
                            outRect.set(halfHPadding, halfVPadding, halfHPadding, halfVPadding)
                        }
                    }
                }
                // 第一行（ 不包括第一个和最后一个
                spanGroupIndex == 0 && spanIndex != 0 && spanIndex != spanCount - 1 -> {
                    outRect.set(halfHPadding, halfVPadding, halfHPadding, halfVPadding)
                }
                // 中间的
                else -> {
                    outRect.set(halfHPadding, halfVPadding, halfHPadding, halfVPadding)
                }
            }
        }
    })
}


/**
 * 是否到底了
 */
val RecyclerView.isBottom: Boolean
    get() = !canScrollVertically(1)

/**
 * 是否到达了顶部
 */
val RecyclerView.isTop: Boolean
    get() = !canScrollVertically(-1)



