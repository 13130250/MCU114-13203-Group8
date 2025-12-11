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
        val payment = intent.getStringExtra("paymentMethod") ?: "未選擇"
        val dine = intent.getStringExtra("diningOption") ?: "未選擇"
        val takeoutName = intent.getStringExtra("takeoutName") ?: ""
        val tableNumber = intent.getStringExtra("tableNumber") ?: ""

        var summaryText = "訂單內容：\n"
        var total = 0
        for (f in foods) {
            summaryText += "${f.name} ×${f.quantity} NT$${f.price * f.quantity}\n"
            total += f.price * f.quantity
        }
        summaryText += "\n總額：NT$$total\n"
        summaryText += "\n付款方式：$payment\n"
        summaryText += "用餐方式：$dine\n"
        if (dine == "外帶" && takeoutName.isNotEmpty()) summaryText += "外帶取餐姓名：$takeoutName\n"
        if (dine == "內用" && tableNumber.isNotEmpty()) summaryText += "桌號：$tableNumber\n"

        txtSummary.text = summaryText

        btnConfirmOrder.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("確認送出訂單")
                .setMessage("確定要送出訂單？")
                .setPositiveButton("確定") { _, _ ->

                    // 顯示動畫容器
                    animationContainer.visibility = android.view.View.VISIBLE

                    // 新增動畫 ImageView
                    val img = ImageView(this)
                    img.layoutParams = FrameLayout.LayoutParams(300, 300, Gravity.CENTER)

                    animationContainer.addView(img)

                    Glide.with(this)
                        .asGif()
                        .load(R.drawable.check_mark)
                        .into(img)

                    // 清空購物車
                    CartManager.clear()

                    // 1.8 秒後跳出原本的選單
                    Handler(Looper.getMainLooper()).postDelayed({
                        animationContainer.visibility = android.view.View.GONE
                        showAfterOrderDialog()
                    }, 1800)
                }
                .setNegativeButton("取消", null)
                .show()
        }

        btnBack.setOnClickListener { finish() }
    }

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
