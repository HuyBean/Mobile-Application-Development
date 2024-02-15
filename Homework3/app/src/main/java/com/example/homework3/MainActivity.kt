package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.loginBtn).setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.coffeeBtn).setOnClickListener{
            val intent = Intent(this, CoffeeActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.currencyBtn).setOnClickListener{
            val intent = Intent(this, CurrencyActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.colorBtn).setOnClickListener{
            val intent = Intent(this, ColorActivity::class.java)
            startActivity(intent)
        }
    }
}