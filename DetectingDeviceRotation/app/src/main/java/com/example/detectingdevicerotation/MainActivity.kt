package com.example.detectingdevicerotation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var myTextView: TextView
    private lateinit var msgET: EditText
    private lateinit var exitBtn: Button
    private lateinit var contentTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("d", "onCreate")
        myTextView = findViewById(R.id.myTextView)

        msgET = findViewById(R.id.msgET)
        exitBtn = findViewById(R.id.exitBtn)
        contentTV = findViewById(R.id.contentTV)

        exitBtn.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("d", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("d", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("d", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("d", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("d", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("d", "onRestart")
    }
}
