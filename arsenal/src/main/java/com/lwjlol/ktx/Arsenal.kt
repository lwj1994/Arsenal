package com.lwjlol.ktx

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build


internal val sdkM = Build.VERSION.SDK_INT >= 23
internal val sdkR = Build.VERSION.SDK_INT >= 30

@SuppressLint("StaticFieldLeak")
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
