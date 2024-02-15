package com.example.homework3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class ColorActivity : AppCompatActivity() {

    private lateinit var colorGrid: GridLayout
    private lateinit var roundButton: Button
    private lateinit var constraintLayout: ConstraintLayout

    private var selectedColor: Int = Color.TRANSPARENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val returnBack = findViewById<ImageButton>(R.id.returnBtnColor).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        colorGrid = findViewById(R.id.colorGrid)
        roundButton = findViewById(R.id.roundButton)
        constraintLayout = findViewById(R.id.constraintLayout)

        setupColorGrid()
        setupRoundButton()
    }

    private fun setupColorGrid() {
        // Set up colors in the GridLayout
        val colors = arrayOf(
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DKGRAY,
            Color.LTGRAY, Color.BLACK, Color.WHITE,
            Color.parseColor("#FFA07A"), // LightSalmon
            Color.parseColor("#00CED1"), // DarkTurquoise
            Color.parseColor("#800080"), // Purple
            Color.parseColor("#FFD700"), // Gold
            Color.parseColor("#FFA500"), // Orange
            Color.parseColor("#8B4513"), // SaddleBrown
            Color.parseColor("#FFC0CB"), // Pink
            Color.parseColor("#8F9779")  // BananaMania
        )


        for (i in 0 until colorGrid.rowCount) {
            for (j in 0 until colorGrid.columnCount) {
                val colorIndex = i * colorGrid.columnCount + j

                if (colorIndex < colors.size) {
                    val colorView = View(this)
                    colorView.layoutParams = GridLayout.LayoutParams().apply {
                        width = 3
                        height = 60
                        columnSpec = GridLayout.spec(j, 1f)
                        rowSpec = GridLayout.spec(i)
                    }

                    colorView.setBackgroundColor(colors[colorIndex])
                    colorView.setOnClickListener {
                        selectedColor = colors[colorIndex]
                    }

                    colorGrid.addView(colorView)
                }
            }
        }
    }

    private fun setupRoundButton() {
        roundButton.setOnClickListener {
            updateBackgroundColor(selectedColor)
        }
    }

    private fun updateBackgroundColor(color: Int) {
        constraintLayout.setBackgroundColor(color)
    }
}
