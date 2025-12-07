package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DiningOptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dine)

        val paymentMethod = intent.getStringExtra("paymentMethod")  // ⭐接收付款方式

        val btnDineIn = findViewById<Button>(R.id.btnDineIn)
        val btnTakeOut = findViewById<Button>(R.id.btnTakeOut)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnDineIn.setOnClickListener {
            val intent = Intent(this, OrderSummaryActivity::class.java)
            intent.putExtra("diningOption", "內用")
            intent.putExtra("paymentMethod", paymentMethod)   // ⭐再傳出去
            startActivity(intent)
        }

        btnTakeOut.setOnClickListener {
            val intent = Intent(this, OrderSummaryActivity::class.java)
            intent.putExtra("diningOption", "外帶")
            intent.putExtra("paymentMethod", paymentMethod)   // ⭐再傳出去
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
