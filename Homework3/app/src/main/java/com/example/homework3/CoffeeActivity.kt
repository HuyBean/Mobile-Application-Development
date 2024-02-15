package com.example.homework3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CoffeeActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var decafRadioButton: RadioButton
    private lateinit var espressoRadioButton: RadioButton
    private lateinit var colombianRadioButton: RadioButton
    private lateinit var creamCheckBox: CheckBox
    private lateinit var sugarCheckBox: CheckBox
    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)

        val returnBack = findViewById<ImageButton>(R.id.returnBtnCoffee).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        radioGroup = findViewById(R.id.radioGroup)
        decafRadioButton = findViewById(R.id.decaf)
        espressoRadioButton = findViewById(R.id.espresso)
        colombianRadioButton = findViewById(R.id.colombian)
        creamCheckBox = findViewById(R.id.cream)
        sugarCheckBox = findViewById(R.id.sugar)
        payButton = findViewById(R.id.payBtn)

        // Set listeners for radio buttons
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.decaf -> {}
                R.id.espresso -> {}
                R.id.colombian -> {}
            }
        }

        // Set listener for checkboxes
        val checkBoxListener = { checkBox: CheckBox -> }

        creamCheckBox.setOnCheckedChangeListener { _, isChecked ->
            checkBoxListener(creamCheckBox)
        }

        sugarCheckBox.setOnCheckedChangeListener { _, isChecked ->
            checkBoxListener(sugarCheckBox)
        }

        // Set listener for Pay button
        payButton.setOnClickListener {
            val selectedOptions = buildSelectedOptionsMessage()
            // Show a Toast with the selected options
            Toast.makeText(this, "Bạn đã chọn các lựa chọn: $selectedOptions", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun buildSelectedOptionsMessage(): String {
        val selectedOptions = mutableListOf<String>()

        // Check which radio button is selected
        if (decafRadioButton.isChecked) {
            selectedOptions.add("Decaf")
        }

        if (espressoRadioButton.isChecked) {
            selectedOptions.add("Espresso")
        }

        if (colombianRadioButton.isChecked) {
            selectedOptions.add("Colombian")
        }

        // Check which checkboxes are checked
        if (creamCheckBox.isChecked) {
            selectedOptions.add("Cream")
        }

        if (sugarCheckBox.isChecked) {
            selectedOptions.add("Sugar")
        }

        return selectedOptions.joinToString(", ")
    }
}
