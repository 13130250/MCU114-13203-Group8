package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // 確保 layout 裡有這些按鈕 ID
        val btnStartOrder = findViewById<Button>(R.id.btnStartOrder)
        val btnMyOrder = findViewById<Button>(R.id.btnMyOrder)

        val btnExit = findViewById<Button>(R.id.btnExit)

        btnStartOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        btnMyOrder.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }



        btnExit.setOnClickListener {
            finishAffinity() // 結束所有 Activity
        }
    }
}
