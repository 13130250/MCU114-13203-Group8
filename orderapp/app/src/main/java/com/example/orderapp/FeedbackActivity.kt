package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val edtFeedback = findViewById<EditText>(R.id.edtFeedback)
        val btnSend = findViewById<Button>(R.id.btnSendFeedback)

        btnSend.setOnClickListener {
            val feedbackText = edtFeedback.text.toString().trim()
            if (feedbackText.isEmpty()) {
                Toast.makeText(this, "請輸入您的評論", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 模擬送出評論到後端
            AlertDialog.Builder(this)
                .setTitle("送出評論")
                .setMessage("確定要送出您的評論嗎？")
                .setPositiveButton("確定") { _, _ ->
                    AlertDialog.Builder(this)
                        .setTitle("送出成功")
                        .setMessage("感謝您的回饋！")
                        .setPositiveButton("回首頁") { _, _ ->
                            val intent = Intent(this, MainMenuActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                        .show()
                }
                .setNegativeButton("取消", null)
                .show()
        }
    }
}
