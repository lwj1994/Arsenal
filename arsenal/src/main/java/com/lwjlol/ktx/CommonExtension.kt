package com.lwjlol.ktx

import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY)
var LAST_THROTTLE_TIME = 0L

/**
 * 节流进行操作，[windowTime] 时间内只允许第一次操作
 * 全局共享一个 [LAST_THROTTLE_TIME] 变量，默认用户同一时间在 ui 内只进行一个节流操作
 * @param windowTime 毫秒
 */
inline fun throttle(
    windowTime: Long = 200,
    block: () -> Unit
) {
    if (System.currentTimeMillis() - LAST_THROTTLE_TIME > windowTime) {
        block()
    }
    LAST_THROTTLE_TIME = System.currentTimeMillis()
}


fun <T : Any> lazyUnsafe(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
    block: () -> T
) = lazy(mode, block)
