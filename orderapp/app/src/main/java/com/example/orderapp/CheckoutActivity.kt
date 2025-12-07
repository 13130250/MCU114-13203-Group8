package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val radioGroupPay = findViewById<RadioGroup>(R.id.radioGroupPay)
        val btnNext = findViewById<Button>(R.id.btnNextDine)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnNext.setOnClickListener {
            val selectedId = radioGroupPay.checkedRadioButtonId
            if (selectedId != -1) {
                val paymentMethod = findViewById<RadioButton>(selectedId).text.toString()
                val intent = Intent(this, DiningOptionActivity::class.java)
                intent.putExtra("paymentMethod", paymentMethod)
                startActivity(intent)
            } else {
                radioGroupPay.requestFocus()
            }
        }

        btnBack.setOnClickListener {
            finish() // 返回上一頁
        }
    }
}
