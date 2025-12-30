package com.example.orderapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val etFeedback = findViewById<EditText>(R.id.et_feedback)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            val feedbackText = etFeedback.text.toString()
            if (feedbackText.isNotEmpty()) {

                val sharedPref = getSharedPreferences("feedback_prefs", Context.MODE_PRIVATE)
                val userPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

                // 取得登入帳號
                val account = userPref.getString("username", "匿名") ?: "匿名"

                // 取得原本評論
                val old = sharedPref.getString("feedback_list", "")

                // 取得當前時間
                val time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())

                // 組合成新評論
                val newEntry = "$time|$account|$feedbackText"
                val updated = if (old.isNullOrEmpty()) newEntry else "$old\n$newEntry"

                // 儲存評論
                sharedPref.edit().putString("feedback_list", updated).apply()

                // 回首頁
                val intent = Intent(this, MainMenuActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                finish()
            }
        }
    }
}
