package com.lwjlol.ktx

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 * 在页面之间传值
 */

private const val LWJLOL_ARGS = "com.lwjlol.ktx:args"

/**
 * 使用委托属性，在 [Activity] 中方便的获取 [Parcelable] 数据
 */
fun <V : Parcelable> activityArgs() = object : ReadOnlyProperty<Activity, V> {
    var value: V? = null
    override fun getValue(thisRef: Activity, property: KProperty<*>): V {
        if (value == null) {
            value = thisRef.intent?.getParcelableExtra(LWJLOL_ARGS)
                ?: throw IllegalArgumentException("There are no activity arguments!")
        }
        return value ?: throw IllegalArgumentException("")
    }
}

fun Intent.setArgs(parcelable: Parcelable) = putExtras(parcelable.asArgs())

/**
 * 将 [Parcelable] 数据转成 key 为 [LWJLOL_ARGS] 的 Bundle 数据
 */
fun Parcelable.asArgs(): Bundle {
    return Bundle().also {
        it.putParcelable(LWJLOL_ARGS, this)
    }
}

fun <V : Any> fragmentArgs() = object : ReadOnlyProperty<Fragment, V> {
    var value: V? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        if (value == null) {
            val args: Bundle = thisRef.arguments
                ?: throw IllegalArgumentException("There are no fragment arguments!")
            val argUntyped = args.get(LWJLOL_ARGS)
                ?: throw IllegalArgumentException("arguments not found at key $LWJLOL_ARGS")
            @Suppress("UNCHECKED_CAST")
            value = argUntyped as V
        }
        return value ?: throw IllegalArgumentException("")
    }
}

fun Fragment.setArgs(parcelable: Parcelable): Fragment {
    arguments = parcelable.asArgs()
    return this
}
