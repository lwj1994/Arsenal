package com.lwjlol.ktx.demo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lwjlol.ktx.*

class MainActivity : AppCompatActivity() {
    private val textView: TextView by lazy {
        findViewById(R.id.textView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("lwjlol-statusBarHeight", window.statusBarHeight.toString())
        Arsenal.init(applicationContext)


        window.decorView.post {
            Log.d("lwjlol-navigationBarHeight", navigationBarHeight.toString())
            Log.d("lwjlol-isNavigationBarExist", isNavigationBarExist.toString())
            textView.text =
                "Android 版本: ${Build.VERSION.SDK_INT}\n分辨率：${screenHeight}*${screenWidth}\n是否是全面屏手机：${isAllScreenDevice}\n状态栏高度：${window.statusBarHeight}\n导航栏高度：${navigationBarHeight}\n导航栏底部横条是否存在：${isNavigationBarExist}"
        }
        window.immersionMode = true

    }
}
