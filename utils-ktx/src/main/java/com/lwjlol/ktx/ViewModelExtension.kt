package com.lwjlol.ktx

import android.view.View
import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import java.io.Serializable


inline fun <reified VM : ViewModel> FragmentActivity.viewModel(
    viewModelStore: ViewModelStore = this.viewModelStore,
    crossinline factory: () -> ViewModelProvider.Factory? = { null },
    crossinline key: () -> String? = { null }
): Lazy<VM> = ViewModelLifecycleAwareLazy(this) {
    val factoryValue = factory() ?: ViewModelProvider.NewInstanceFactory()
    val keyValue = key()
    if (keyValue == null) {
        ViewModelProvider(viewModelStore, factoryValue).get(VM::class.java)
    } else {
        ViewModelProvider(viewModelStore, factoryValue).get(keyValue, VM::class.java)
    }
}


inline fun <reified VM : ViewModel> Fragment.activityViewModel(
    crossinline factory: () -> ViewModelProvider.Factory? = { null },
    crossinline key: () -> String? = { null }
): Lazy<VM> = ViewModelLifecycleAwareLazy(this) {
    val factoryValue = factory() ?: ViewModelProvider.NewInstanceFactory()
    val keyValue = key()
    if (keyValue == null) {
        ViewModelProvider(requireActivity().viewModelStore, factoryValue).get(VM::class.java)
    } else {
        ViewModelProvider(requireActivity().viewModelStore, factoryValue).get(
            keyValue,
            VM::class.java
        )
    }
}

/**
 *
 * 在父 fragment 寻找一个现有的 [ViewModel] 找不到就在父 Fragment 里创建一个
 *
 * 只能在子 fragment 里使用
 *
 * @param factory
 * @param keyFactory
 * @return [ViewModelLifecycleAwareLazy]
 */
inline fun <reified VM : ViewModel> Fragment.parentViewModel(
    crossinline factory: () -> ViewModelProvider.Factory? = { null },
    crossinline keyFactory: () -> String? = { null }
): Lazy<VM> = ViewModelLifecycleAwareLazy(this) {
    val fragment = parentFragment
        ?: throw IllegalArgumentException("There is no parent fragment for ${this::class.java.simpleName}!")
    val keyValue = keyFactory()
    val factoryValue = factory() ?: ViewModelProvider.NewInstanceFactory()
    if (keyValue == null) {
        ViewModelProvider(fragment.viewModelStore, factoryValue).get(VM::class.java)
    } else {
        ViewModelProvider(fragment.viewModelStore, factoryValue).get(keyValue, VM::class.java)

    }
}

inline fun <reified VM : ViewModel> Fragment.viewModel(
    viewModelStore: ViewModelStore = this.viewModelStore,
    crossinline factory: () -> ViewModelProvider.Factory? = { null },
    crossinline key: () -> String? = { null }
): Lazy<VM> = ViewModelLifecycleAwareLazy(this) {
    val factoryValue = factory() ?: ViewModelProvider.NewInstanceFactory()
    val keyValue = key()
    if (keyValue == null) {
        ViewModelProvider(viewModelStore, factoryValue).get(VM::class.java)
    } else {
        ViewModelProvider(viewModelStore, factoryValue).get(keyValue, VM::class.java)
    }
}

inline fun <reified VM : ViewModel> View.viewModel(
    crossinline factory: () -> ViewModelProvider.Factory? = { null },
    crossinline key: () -> String? = { null }
): Lazy<VM> = lazy(
    LazyThreadSafetyMode.NONE
) {
    val factoryValue = factory() ?: ViewModelProvider.NewInstanceFactory()
    val keyValue = key()
    val store = (context as FragmentActivity).viewModelStore
    if (keyValue == null) {
        ViewModelProvider(store, factoryValue).get(VM::class.java)
    } else {
        ViewModelProvider(store, factoryValue).get(keyValue, VM::class.java)
    }
}

inline fun <reified VM : ViewModel> View.getViewModel(
    factory: ViewModelProvider.Factory? = null, key: String? = null
): VM {
    val context = context as? FragmentActivity
        ?: throw IllegalStateException("view context is not FragmentActivity")
    val f = factory ?: ViewModelProvider.NewInstanceFactory()
    return if (key == null) {
        ViewModelProvider(context.viewModelStore, f).get(VM::class.java)
    } else {
        ViewModelProvider(context.viewModelStore, f).get(key, VM::class.java)
    }
}