package com.example.orderapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val btnSendFeedback = findViewById<Button>(R.id.btnSendFeedback)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnSendFeedback.setOnClickListener {
            // 送出回饋功能可依需求實作
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
