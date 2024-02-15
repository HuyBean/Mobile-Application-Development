package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val returnButton = findViewById<ImageButton>(R.id.returnBtn).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val usernameEditText = findViewById<TextInputEditText>(R.id.Username)
        val passwordEditText = findViewById<TextInputEditText>(R.id.Password)
        val goBtn = findViewById<Button>(R.id.goBtn)
    }
}