package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnStartOrder = findViewById<Button>(R.id.btnStartOrder)
        val btnViewOrders = findViewById<Button>(R.id.btnMyOrder)
        val btnLoginLogout = findViewById<Button>(R.id.btnLogin)

        btnStartOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        btnViewOrders.setOnClickListener {
            // 尚未實作
        }

        btnLoginLogout.setOnClickListener {
            // 尚未實作
        }

    }
}
