package com.example.lab3

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtShow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 狀態列綠色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#05352E")
        }

        txtShow = findViewById(R.id.txtShow)
        txtShow.text = "電話號碼："

        val buttonIds = listOf(
            R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven,
            R.id.btnEight, R.id.btnNine, R.id.btnStar, R.id.btnClear
        )

        val myListener = View.OnClickListener { v ->
            val currentText = txtShow.text.toString()
            when (v.id) {
                R.id.btnZero -> txtShow.text = currentText + "0"
                R.id.btnOne -> txtShow.text = currentText + "1"
                R.id.btnTwo -> txtShow.text = currentText + "2"
                R.id.btnThree -> txtShow.text = currentText + "3"
                R.id.btnFour -> txtShow.text = currentText + "4"
                R.id.btnFive -> txtShow.text = currentText + "5"
                R.id.btnSix -> txtShow.text = currentText + "6"
                R.id.btnSeven -> txtShow.text = currentText + "7"
                R.id.btnEight -> txtShow.text = currentText + "8"
                R.id.btnNine -> txtShow.text = currentText + "9"
                R.id.btnStar -> txtShow.text = currentText + "*"
                R.id.btnClear -> txtShow.text = "電話號碼："
            }
        }

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener(myListener)
        }
    }
}
