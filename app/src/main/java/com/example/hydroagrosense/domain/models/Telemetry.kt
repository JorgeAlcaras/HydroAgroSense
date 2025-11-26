package com.example.hydroagrosense.domain.models

data class Telemetry(
    val kind: String?,           // "telemetry"
    val deviceId: String?,
    val Temperature: Double?,
    val Air: Double?,
    val Soil: Double?,
    val Light: Double?,
    val date: String?,
    val configVersion: Int?
)