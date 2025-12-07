package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OrderSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val txtSummary = findViewById<TextView>(R.id.txtOrderSummary)
        val btnNext = findViewById<Button>(R.id.btnNextFeedback)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val foods = intent.getParcelableArrayListExtra<FoodItem>("foods") ?: arrayListOf()
        val payment = intent.getStringExtra("paymentMethod")
        val dine = intent.getStringExtra("diningOption")

        var summaryText = "訂單內容：\n"
        var total = 0

        for (f in foods) {
            summaryText += "${f.name} ×${f.quantity}  NT$${f.price * f.quantity}\n"
            total += f.price * f.quantity
        }

        summaryText += "\n總額：NT$$total\n"
        summaryText += "\n付款方式：$payment\n"
        summaryText += "用餐方式：$dine\n"

        txtSummary.text = summaryText

        btnNext.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        btnBack.setOnClickListener { finish() }
    }
}
