package com.example.hydroagrosense.domain.models

data class ConfigDto(
    val deviceId: String,
    val version: Int,
    val lastUpdated: String?,
    val sensors: SensorsConfig,
    val irrigation: IrrigationConfig,
    val sampling: SamplingConfig
)