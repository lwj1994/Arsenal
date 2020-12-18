package com.lwjlol.ktx

import android.content.Context


lateinit var ktxContext: Context


object Initializer {

    /**
     * 在 [android.app.Application] 初始化时，将 context 传入
     */
    fun initUtilsKtx(context: Context) {
        ktxContext = context.applicationContext
    }

    val targetCode: Int
        get() = ktxContext.applicationInfo.targetSdkVersion
}