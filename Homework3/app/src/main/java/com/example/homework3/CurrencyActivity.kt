package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.*

class CurrencyActivity : AppCompatActivity() {
    private lateinit var dropDown1: Spinner
    private lateinit var dropDown2: Spinner
    private lateinit var inputAmount: TextInputEditText
    private lateinit var convertBtn: Button
    private lateinit var resultText: EditText
    private lateinit var selectedCurrencyTextView: TextView
    private lateinit var selectedCurrencyTextView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        dropDown1 = findViewById(R.id.dropDown1)
        dropDown2 = findViewById(R.id.dropDown2)
        inputAmount = findViewById(R.id.inputAmount)
        convertBtn = findViewById(R.id.convertBtn)
        resultText = findViewById(R.id.resultText)
        selectedCurrencyTextView = findViewById(R.id.selectedCurrencyTextView)
        selectedCurrencyTextView2 = findViewById(R.id.selectedCurrencyTextView2)



        val returnBack = findViewById<ImageButton>(R.id.returnBack).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val currencyOptions = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dropDown1.adapter = adapter
        dropDown2.adapter = adapter

        dropDown1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle selection
                selectedCurrencyTextView.text = "${dropDown1.selectedItem}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        dropDown2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCurrencyTextView2.text = "${dropDown2.selectedItem}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Set click listener for convert button
        convertBtn.setOnClickListener {
            calculateCurrency()
        }
    }

    private fun calculateCurrency() {
        val exchangeRates = mapOf(
            "VND" to 24470.00,
            "USD" to 1.0,
            "EUR" to 0.92,
            "JPN" to 147.32,
            "KRW" to 1331.21,
            "CNY" to 7.09
        )
        val amountToConvert = inputAmount.text.toString().toDoubleOrNull() ?: 0.0

        val fromCurrency = dropDown1.selectedItem.toString()
        val toCurrency = dropDown2.selectedItem.toString()

        val exchangeRateFrom = exchangeRates[fromCurrency] ?: 1.0
        val exchangeRateTo = exchangeRates[toCurrency] ?: 1.0
        val convertedAmount = amountToConvert * exchangeRateTo / exchangeRateFrom

        resultText.setText(convertedAmount.toString())

    }
}
