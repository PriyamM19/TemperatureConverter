package com.example.temperatureconverter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DataTemp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temp_data)

        // Initialize views
        val temperatureTextView = findViewById<TextView>(R.id.temperatureInfo)
        val weatherTextView = findViewById<TextView>(R.id.weatherInfo)
        val flagImageView = findViewById<ImageView>(R.id.weatherFlag)

        // Retrieve the temperature from the intent
        val temperature = intent.getDoubleExtra("temperature", 0.0)

        // Determine weather based on temperature
        val weatherInfo = when {
            temperature >= 30 -> "Sunny"
            temperature in 15.0..29.9 -> "Cloudy"
            else -> "Rainy"
        }

        // Update the UI with temperature and weather information
        temperatureTextView.text = "Temperature: $temperature°C"
        weatherTextView.text = "Weather: $weatherInfo"

         }
}
