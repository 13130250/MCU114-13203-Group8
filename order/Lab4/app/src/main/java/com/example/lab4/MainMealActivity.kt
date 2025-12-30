package com.example.lab4

import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity



class MainMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_meal)

        val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
        val btnDone = findViewById<Button>(R.id.btnDone)

        btnDone.setOnClickListener {
            // 找到被選中的 RadioButton 的 ID
            val selectedId = rgOptions.checkedRadioButtonId
            // 如果有選項被選中
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val selectedText = selectedRadioButton.text.toString()

                // 建立一個 Intent 來存放要回傳的資料
                val resultIntent = Intent()
                resultIntent.putExtra("meal_name", selectedText)

                // 設定結果為 OK，並附上帶有資料的 Intent
                setResult(Activity.RESULT_OK, resultIntent)
            } else {
                // 如果沒有選擇任何東西，設定結果為 CANCELED
                setResult(Activity.RESULT_CANCELED)
            }

            // 關閉目前的 Activity，返回上一個畫面
            finish()
        }
    }
}