package com.example.project_startactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val message = intent.getStringExtra("Hello")
        val textView: TextView = findViewById(R.id.textView)
        textView.text = message

        findViewById<Button>(R.id.backBtn).setOnClickListener(){
            val reply = "This is value from SecondActivity to MainActivity"
            val replyIntent = Intent()
            intent.putExtra("Reply", reply)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
}