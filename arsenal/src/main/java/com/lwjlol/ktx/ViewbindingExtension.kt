package com.lwjlol.ktx

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding: FHomeWorkoutDetailsBinding by viewBinding()
 * with your binding class and access it as you normally would.
 *
 */

inline fun <reified T : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate(T::class.java, this)

/**
 * @param fragment
 * @param targetView
 */
class FragmentViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    private val fragment: Fragment,
    private val targetView: View? = null
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): T {
        binding?.let { return it }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            Log.w(
                "ViewBindingDelegate",
                "Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!"
            )
        }

        fragment.viewLifecycleOwnerLiveData.observe(fragment, Observer { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(ON_DESTROY)
                fun onDestroy() {
                    binding = null
                }
            })
        })

        @Suppress("UNCHECKED_CAST")

        binding = if (targetView != null) {
            bindMethod.invoke(null, targetView) as T
        } else try {
            bindMethod.invoke(null, thisRef.requireView()) as T
        } catch (e: InvocationTargetException) {
            // 防止被嵌套 获取第2层的 view
            try {
                bindMethod.invoke(null, (thisRef.requireView() as ViewGroup).getChildAt(0)) as T
            } catch (e: InvocationTargetException) {
                // 防止被嵌套 获取第3层的 view
                val child =
                    ((thisRef.requireView() as ViewGroup).getChildAt(0) as ViewGroup).getChildAt(
                        0
                    )
                bindMethod.invoke(null, child) as T
            }
        }
        return binding!!
    }
}

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding: FHomeWorkoutDetailsBinding by viewBinding()
 * with your binding class and access it as you normally would.
 */
inline fun <reified T : ViewBinding> ViewGroup.viewBinding() =
    ViewBindingDelegate(T::class.java)

class ViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
) : ReadOnlyProperty<ViewGroup, T> {
    private var binding: T? = null

    override fun getValue(
        thisRef: ViewGroup,
        property: KProperty<*>
    ): T {
        binding?.let { return it }
        val inflateMethod =
            bindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java)
        @Suppress("UNCHECKED_CAST")
        binding = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef) as T
        return binding!!
    }
}
