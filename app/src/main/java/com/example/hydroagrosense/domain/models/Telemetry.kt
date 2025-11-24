package com.example.hydroagrosense.domain.models

data class Telemetry(
    val kind: String?,           // "telemetry"
    val deviceId: String?,
    val temperature: Double?,
    val air: Double?,
    val soil: Double?,
    val light: Double?,
    val date: String?,
    val configVersion: Int?
)