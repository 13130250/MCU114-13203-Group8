package com.example.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val tvOrderSummary = findViewById<TextView>(R.id.tvOrderSummary)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        // 從啟動此 Activity 的 Intent 中獲取傳遞過來的資料
        val mainMeal = intent.getStringExtra("main_meal") ?: "無"
        val sideDish = intent.getStringExtra("side_dish") ?: "無"
        val drink = intent.getStringExtra("drink") ?: "無"

        // 組合訂單摘要文字
        val orderText = "主餐: $mainMeal\n\n" +
                "附餐: $sideDish\n\n" +
                "飲料: $drink"

        // 將組合好的文字設定到 TextView 上
        tvOrderSummary.text = orderText

        // 為確認按鈕設定點擊事件，點擊後關閉此頁面
        btnConfirm.setOnClickListener {
            finish()
        }
    }
}