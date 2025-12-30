package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {

    private val foodList = listOf(
        FoodItem("牛肉丼飯", 150, 0, "飯類"),
        FoodItem("海鮮丼飯", 190, 0, "飯類"),
        FoodItem("牛肉麵", 120, 0, "麵類"),
        FoodItem("酸辣麵", 60, 0, "麵類"),
        FoodItem("炒飯", 70, 0, "飯類"),
        FoodItem("紅茶", 20, 0, "飲料"),
        FoodItem("鮮奶茶", 40, 0, "飲料"),
    )

    private lateinit var adapter: FoodAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var spinnerCategory: Spinner
    private lateinit var tvCartCount: TextView
    private lateinit var btnGoCheckout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        recycler = findViewById(R.id.recyclerFood)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        tvCartCount = findViewById(R.id.tvCartCount)
        btnGoCheckout = findViewById(R.id.btnNextCheckout)

        adapter = FoodAdapter(foodList) { item, view ->
            // 加入購物車回調，並播放一個簡單「縮放」動畫
            CartManager.add(item)
            updateCartCount()
            animateAddToCart(view)
            Toast.makeText(this, "${item.name} 已加入購物車", Toast.LENGTH_SHORT).show()
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // 分類 Spinner
        val categories = listOf("全部", "飯類", "麵類", "飲料")
        val spAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = spAdapter
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val cat = categories[position]
                if (cat == "全部") adapter.updateList(foodList)
                else adapter.updateList(foodList.filter { it.category == cat })
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }

        btnGoCheckout.setOnClickListener {
            // 若購物車為空，提醒
            if (CartManager.getItems().isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("購物車為空")
                    .setMessage("請先加入餐點到購物車。")
                    .setPositiveButton("好", null)
                    .show()
                return@setOnClickListener
            }
            // 進結帳
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        updateCartCount()
    }

    private fun updateCartCount() {
        val count = CartManager.getItems().sumOf { it.quantity }
        tvCartCount.text = "購物車：$count"
    }

    private fun animateAddToCart(view: View) {
        val anim = android.animation.ObjectAnimator.ofFloat(view, "translationY", 0f, -40f, 0f)
        anim.duration = 400
        anim.start()
    }
}
