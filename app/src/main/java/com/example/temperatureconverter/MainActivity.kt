package com.example.temperatureconverter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTemperature: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView
    private lateinit var btnInfo: Button

    private var fromUnit: String = "Celsius"
    private var toUnit: String = "Fahrenheit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTemperature = findViewById(R.id.amountInput)
        spinnerFrom = findViewById(R.id.currencyFromSpinner)
        spinnerTo = findViewById(R.id.currencyToSpinner)
        btnConvert = findViewById(R.id.convertButton)
        tvResult = findViewById(R.id.convertedAmount)
        btnInfo = findViewById(R.id.countryInfoButton)

        // Set up the spinners
        val options = arrayOf("Celsius", "Fahrenheit")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        // Set up the spinner listeners
        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                fromUnit = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                toUnit = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set up the button listener
        btnConvert.setOnClickListener {
            convertTemperature()
        }

        // Set up the info button listener
        btnInfo.setOnClickListener {
            showTemperatureInfo()
        }
    }

    private fun convertTemperature() {
        val temperature = etTemperature.text.toString().toDoubleOrNull()
        if (temperature == null) {
            Toast.makeText(this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (fromUnit to toUnit) {
            "Celsius" to "Fahrenheit" -> celsiusToFahrenheit(temperature)
            "Fahrenheit" to "Celsius" -> fahrenheitToCelsius(temperature)
            else -> temperature
        }

        tvResult.text = "Converted Temperature: $result"
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return (celsius * 9 / 5) + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun showTemperatureInfo() {
        val temperature = etTemperature.text.toString().toDoubleOrNull()
        if (temperature == null) {
            Toast.makeText(this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, DataTemp::class.java).apply {
            putExtra("temperature", temperature)
        }
        startActivity(intent)
    }
}
