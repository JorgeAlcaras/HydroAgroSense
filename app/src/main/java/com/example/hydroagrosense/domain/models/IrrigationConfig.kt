package com.example.hydroagrosense.domain.models

data class IrrigationConfig(
    val autoEnabled: Boolean,
    val mode: String,
    val minOnSeconds: Int,
    val maxOnSeconds: Int,
    val manualDefaultSeconds: Int,
    val cooldownSeconds: Int
)