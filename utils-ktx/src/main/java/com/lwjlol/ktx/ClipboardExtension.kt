package com.lwjlol.ktx

import android.content.ClipData
import android.content.ClipboardManager
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity


/**
 * 复制到剪贴板
 */
@UiThread
fun copyToClipboard(text: String) {
    val clipboard =
        ktxContext.getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager
    val myClip = ClipData.newPlainText("text", text)
    clipboard.setPrimaryClip(myClip)
}