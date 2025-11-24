package com.example.hydroagrosense.domain.models

data class SensorsConfig(
    val soilMoisture: RangeConfig,
    val airHumidity: RangeConfig,
    val temperature: RangeConfig,
    val lightIntensity: RangeConfig
)