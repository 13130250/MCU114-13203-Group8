package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {

    private val foodList = listOf(
        FoodItem("牛肉麵", 120, 0),
        FoodItem("酸辣麵", 60, 0),
        FoodItem("餛飩麵", 60, 0),
        FoodItem("炒飯", 70, 0),
        FoodItem("炒麵", 70, 0),
        FoodItem("紅茶", 20, 0),
        FoodItem("鮮奶茶", 40, 0),

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val recycler = findViewById<RecyclerView>(R.id.recyclerFood)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = FoodAdapter(foodList)

        val btnNext = findViewById<Button>(R.id.btnNextCheckout)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnNext.setOnClickListener {

            val selectedFoods = foodList.filter { it.quantity > 0 }

            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putParcelableArrayListExtra("foods",
                ArrayList(selectedFoods)
            )
            startActivity(intent)
        }

        btnBack.setOnClickListener { finish() }
    }
}
