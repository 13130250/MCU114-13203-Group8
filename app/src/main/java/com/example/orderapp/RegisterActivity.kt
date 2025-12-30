package com.example.orderapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        val prefs = getSharedPreferences("users", MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val name = edtName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "請填寫完整資料", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val keyPassword = "pwd_$email"
            if (prefs.contains(keyPassword)) {
                Toast.makeText(this, "此帳號已註冊，請直接登入", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.edit()
                .putString("name_$email", name)
                .putString("pwd_$email", password)
                .apply()

            Toast.makeText(this, "註冊成功，請登入", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
