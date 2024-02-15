package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Button, EditText, and TextView by their IDs
        val myButton: Button = findViewById(R.id.myButton)
        val myEditText: EditText = findViewById(R.id.myEditText)
        val myTextView: TextView = findViewById(R.id.myTextView)

        Log.i("testing", "onCreate")
        // Set a click listener for the Button
        myButton.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("testing", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("d", "onResume")
    }


    override fun onStop() {
        super.onStop()
        Log.i("d", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("d", "onRestart")
    }

}