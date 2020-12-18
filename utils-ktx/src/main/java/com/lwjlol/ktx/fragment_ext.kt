package com.lwjlol.ktx

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.showSafely(
    manager: FragmentManager,
    tag: String? = null
) {
    if (manager.isStateSaved) return
    show(manager, tag)
}
