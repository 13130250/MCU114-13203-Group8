package com.example.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // 登入成功 -> 主選單
                try {
                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "無法跳轉到主選單", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "請輸入帳號與密碼", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            Toast.makeText(this, "註冊功能尚未實作", Toast.LENGTH_SHORT).show()
        }
    }
}
