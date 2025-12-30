package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class DrinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
        val btnDone = findViewById<Button>(R.id.btnDone)

        btnDone.setOnClickListener {
            val selectedId = rgOptions.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val selectedText = selectedRadioButton.text.toString()

                val resultIntent = Intent()
                // 使用新的 key 來回傳資料
                resultIntent.putExtra("drink_name", selectedText)
                setResult(Activity.RESULT_OK, resultIntent)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}