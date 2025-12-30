package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 直接導向 Login 畫面
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
