package com.lwjlol.ktx

import android.content.Context


lateinit var ktxContext: Context

fun initUtilsKtx(context: Context) {
    ktxContext = context.applicationContext
}