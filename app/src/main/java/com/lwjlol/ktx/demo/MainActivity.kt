package com.lwjlol.ktx.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lwjlol.ktx.isNavigationBarExist
import com.lwjlol.ktx.navigationBarHeight
import com.lwjlol.ktx.statusBarHeight

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("lwjlol-statusBarHeight", window.statusBarHeight.toString())
        window.decorView.post {
            Log.d("lwjlol-navigationBarHeight", navigationBarHeight.toString())
            Log.d("lwjlol-isNavigationBarExist", isNavigationBarExist.toString())
        }


    }
}
