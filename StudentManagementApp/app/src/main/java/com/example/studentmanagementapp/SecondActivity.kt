package com.example.studentmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    var saveButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        saveButton = findViewById(R.id.saveButton2)
//        saveButton?.setOnClickListener {
//            val i = Intent(this@SecondActivity, MainActivity::class.java)
//            startActivity(i)
//        }
    }
}