package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class OrderSummaryActivity : AppCompatActivity() {

    private lateinit var txtSummary: TextView
    private lateinit var btnConfirmOrder: Button
    private lateinit var btnBack: Button
    private lateinit var animationContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        txtSummary = findViewById(R.id.txtOrderSummary)
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder)
        btnBack = findViewById(R.id.btnBack)
        animationContainer = findViewById(R.id.animationContainer)

        val foods = CartManager.getItems()

        // 顯示訂單摘要
        var total = 0
        val summaryText = buildString {
            append("訂單內容：\n")
            foods.forEach {
                append("${it.name} ×${it.quantity} NT$${it.price * it.quantity}\n")
                total += it.price * it.quantity
            }
            append("\n總額：NT$$total")
        }

        txtSummary.text = summaryText

        // 送出訂單
        btnConfirmOrder.setOnClickListener {
            if (foods.isEmpty()) {
                Toast.makeText(this, "購物車是空的", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("確認送出訂單")
                .setMessage("確定要送出訂單？")
                .setPositiveButton("確定") { _, _ ->

                    val user = SessionManager.getCurrentUser(this)
                    if (user == null) {
                        Toast.makeText(this, "尚未登入", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    val itemsText = foods.joinToString("\n") {
                        "${it.name} x${it.quantity}  NT$${it.price * it.quantity}"
                    }

                    // ✅ 存訂單（只在這裡存一次）
                    DBHelper(this).insertOrder(user, itemsText, total)
                    Toast.makeText(this, "已存訂單", Toast.LENGTH_LONG).show()

                    // 動畫
                    animationContainer.visibility = android.view.View.VISIBLE
                    val img = ImageView(this)
                    img.layoutParams = FrameLayout.LayoutParams(300, 300, Gravity.CENTER)
                    animationContainer.removeAllViews()
                    animationContainer.addView(img)

                    Glide.with(this)
                        .asGif()
                        .load(R.drawable.check_mark)
                        .into(img)

                    CartManager.clear()

                    Handler(Looper.getMainLooper()).postDelayed({
                        animationContainer.visibility = android.view.View.GONE
                        showAfterOrderDialog()
                    }, 1500)
                }
                .setNegativeButton("取消", null)
                .show()
        }

        btnBack.setOnClickListener { finish() }
    }

    // ✅ 只顯示成功後選項，不存資料
    private fun showAfterOrderDialog() {
        AlertDialog.Builder(this)
            .setTitle("訂單送出成功")
            .setMessage("請選擇接下來要做的事")
            .setPositiveButton("回首頁") { _, _ ->
                startActivity(Intent(this, MainMenuActivity::class.java))
                finish()
            }
            .setNegativeButton("填寫評論") { _, _ ->
                startActivity(Intent(this, FeedbackActivity::class.java))
            }
            .show()
    }
}
