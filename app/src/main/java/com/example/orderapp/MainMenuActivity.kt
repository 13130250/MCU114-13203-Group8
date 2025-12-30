package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ 沒登入就不能進主頁
        if (SessionManager.getCurrentUser(this) == null) {
            val i = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(i)
            finish()
            return
        }

        setContentView(R.layout.activity_main_menu)

        val btnStartOrder = findViewById<Button>(R.id.btnStartOrder)
        val btnMyOrder = findViewById<Button>(R.id.btnMyOrder)
        val btnViewFeedbackList = findViewById<Button>(R.id.btn_feedback)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnStartOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        btnMyOrder.setOnClickListener {
            startActivity(Intent(this, MyOrdersActivity::class.java))
        }

        btnViewFeedbackList.setOnClickListener {
            val intent = Intent(this, FeedbackListActivity::class.java)
            startActivity(intent)
        }

        // ✅ 登出：清 session + 回登入 + 清 back stack
        btnExit.setOnClickListener {
            SessionManager.logout(this)

            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }
}

