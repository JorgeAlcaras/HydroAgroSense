package com.example.hydroagrosense.domain.models

data class IrrigationHistory(
    val kind: String?,              // "irrigation_history"
    val deviceId: String?,
    val reason: String?,
    val requestedDuration: Int?,
    val realDuration: Int?,
    val date: String?,
    val status: Map<String, String>?,  // { "Temperature": "OK", ... }
    val before: Telemetry?,
    val after: Telemetry?
)
