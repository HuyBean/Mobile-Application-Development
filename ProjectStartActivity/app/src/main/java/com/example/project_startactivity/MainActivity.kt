package com.example.project_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.gotoSecondBtn).setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.gotoSecondBtn).setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("Hello", "This is value from MainActivity to SecondActivity")
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    val REQUEST_CODE = 1111
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
                val reply = data!!.getStringExtra("Reply")

                val textView: TextView = findViewById(R.id.textView)
                textView.text = reply
            }
        }
    }
}