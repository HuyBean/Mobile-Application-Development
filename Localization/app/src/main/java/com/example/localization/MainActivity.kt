package com.example.localization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import androidx.appcompat.widget.Toolbar
import com.example.localization.R.id.appBarLayout
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var englishButton: Button
    private lateinit var vietnameseButton: Button
    private lateinit var contentTv: TextView
    private lateinit var imageFlag: ImageView
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var toolbar: Toolbar

    object LocaleUtils {
        fun setLocale(context: Context, language: String) {
            // Tạo một đối tượng Locale từ mã ngôn ngữ được chỉ định
            val locale = Locale(language)
            // Đặt locale mặc định là đối tượng Locale vừa tạo
            Locale.setDefault(locale)
            val resources: Resources = context.resources
            val config: Configuration = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần từ layout
        englishButton = findViewById<Button>(R.id.englishButton)
        vietnameseButton = findViewById<Button>(R.id.vietnameseButton)
        contentTv = findViewById(R.id.contentTv)
        appBarLayout = findViewById(R.id.appBarLayout)
        toolbar = findViewById(R.id.toolbar)
        imageFlag = findViewById(R.id.imageView)

        // Thiết lập sự kiện nhấn nút cho nút English
        englishButton.setOnClickListener {
            LocaleUtils.setLocale(this, "en")
            contentTv.text = getString(R.string.description)
            toolbar.title = getString(R.string.app_name)
            imageFlag.setImageResource(R.drawable.flag)
        }

        // Thiết lập sự kiện nhấn nút cho nút Tiếng Việt
        vietnameseButton.setOnClickListener {
            LocaleUtils.setLocale(this, "vi")
            contentTv.text = getString(R.string.description)
            toolbar.title = getString(R.string.app_name)
            imageFlag.setImageResource(R.drawable.flag)
        }
    }

}

