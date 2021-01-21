package com.lwjlol.ktx

import android.content.Context


internal lateinit var ktxContext: Context


object Arsenal {

    /**
     * 在 [android.app.Application] 初始化时，将 context 传入
     */
    fun init(context: Context) {
        ktxContext = context.applicationContext
    }

    val targetCode: Int
        get() = ktxContext.applicationInfo.targetSdkVersion
}
