package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 簡單 layout
        setContentView(R.layout.activity_main)

        // 直接跳到主選單，結束自己
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish()
    }
}
