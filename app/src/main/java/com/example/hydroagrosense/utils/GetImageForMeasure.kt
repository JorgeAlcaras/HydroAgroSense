package com.example.hydroagrosense.utils

fun getImageForMeasure(measureName: String): String {
    return when (measureName) {
        "Temperature" -> "file:///android_asset/icons/temperature.png"
        "Air Humidity" -> "file:///android_asset/icons/air_humidity.png"
        "Soil Moisture" -> "file:///android_asset/icons/soil_moisture.png"
        "Light Intensity" -> "file:///android_asset/icons/sunshine.png"
        else -> "file:///android_asset/icons/plant.png"
    }
}
