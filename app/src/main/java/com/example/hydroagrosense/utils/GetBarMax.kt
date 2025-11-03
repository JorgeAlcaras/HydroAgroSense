package com.example.hydroagrosense.utils

fun getBarMax(measureName: String): Double {
    return when (measureName) {
        "Temperature" -> 50.0
        "Air Humidity" -> 100.0
        "Soil Moisture" -> 100.0
        "Light Intensity" -> 100.0
        else -> 100.0
    }
}
