package com.lwjlol.ktx

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object KeyboardUtil {
  fun showKeyboard(view: View) {
    try {
      view.requestFocus()
      val inputManager = ktxContext.getSystemService(
          Context.INPUT_METHOD_SERVICE
      ) as InputMethodManager
      inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)

    } catch (e: Exception) {
    }
  }

  fun hideKeyboard(view: View) {
    try {
      val imm = ktxContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      if (!imm.isActive) {
        return
      }
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
    }
  }

  fun isKeyboardShowed(view: View): Boolean {
    try {
      val inputManager = ktxContext.getSystemService(
          Context.INPUT_METHOD_SERVICE
      ) as InputMethodManager
      return inputManager.isActive(view)
    } catch (e: Exception) {
    }
    return false
  }

  fun toggleSoftInput() {
    try {
      val imm = ktxContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      if (!imm.isActive) {
        return
      }
      imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0)
    } catch (e: Exception) {
    }
  }
}


fun EditText.showKeyboard() {
  requestFocus()
  isFocusable = true
  isFocusableInTouchMode = true
  isCursorVisible = true
  KeyboardUtil.showKeyboard(this)
}

fun EditText.hideKeyboard() {
  requestFocus()
  isFocusable = false
  isFocusableInTouchMode = true
  isCursorVisible = false
  KeyboardUtil.hideKeyboard(this)
}