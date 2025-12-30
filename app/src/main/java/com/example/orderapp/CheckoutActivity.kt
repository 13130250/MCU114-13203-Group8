package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val spinnerDining = findViewById<Spinner>(R.id.spinnerDining)
        val spinnerPay = findViewById<Spinner>(R.id.spinnerPay)
        val edtTakeoutName = findViewById<EditText>(R.id.edtTakeoutName)
        val edtTableNumber = findViewById<EditText>(R.id.edtTableNumber)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        val dineOptions = listOf("內用", "外帶")
        val dineAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dineOptions)
        dineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDining.adapter = dineAdapter

        spinnerDining.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (dineOptions[position] == "內用") {
                    edtTableNumber.visibility = View.VISIBLE
                    edtTakeoutName.visibility = View.GONE
                } else {
                    edtTableNumber.visibility = View.GONE
                    edtTakeoutName.visibility = View.VISIBLE
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val payOptions = listOf("現金", "信用卡", "LinePay")
        val payAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, payOptions)
        payAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPay.adapter = payAdapter

        btnConfirm.setOnClickListener {
            val items: List<FoodItem> = CartManager.getItems()
            if (items.isEmpty()) {
                Toast.makeText(this, "購物車是空的", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, OrderSummaryActivity::class.java)
            intent.putExtra("paymentMethod", spinnerPay.selectedItem.toString())
            intent.putExtra("diningOption", spinnerDining.selectedItem.toString())
            intent.putExtra("takeoutName", edtTakeoutName.text.toString())
            intent.putExtra("tableNumber", edtTableNumber.text.toString())
            startActivity(intent)
        }

        btnBack.setOnClickListener { finish() }
    }
}
