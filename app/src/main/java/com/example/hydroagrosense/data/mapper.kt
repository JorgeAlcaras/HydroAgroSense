package com.example.hydroagrosense.data

import com.example.hydroagrosense.data.local.*
import com.example.hydroagrosense.domain.models.*
import com.google.gson.Gson

private val gson = Gson()

fun ConfigDto.toEntity(): ConfigEntity =
    ConfigEntity(
        deviceId = deviceId,
        version = version,
        lastUpdated = lastUpdated,
        soilMin = sensors.soilMoisture.min,
        soilMax = sensors.soilMoisture.max,
        airMin = sensors.airHumidity.min,
        airMax = sensors.airHumidity.max,
        tempMin = sensors.temperature.min,
        tempMax = sensors.temperature.max,
        lightMin = sensors.lightIntensity.min,
        lightMax = sensors.lightIntensity.max,
        autoEnabled = irrigation.autoEnabled,
        mode = irrigation.mode,
        minOnSeconds = irrigation.minOnSeconds,
        maxOnSeconds = irrigation.maxOnSeconds,
        manualDefaultSeconds = irrigation.manualDefaultSeconds,
        cooldownSeconds = irrigation.cooldownSeconds,
        readIntervalSeconds = sampling.readIntervalSeconds,
        sendIntervalSeconds = sampling.sendIntervalSeconds,
        configRefreshSeconds = sampling.configRefreshSeconds
    )

fun Telemetry.toEntity(): TelemetryEntity =
    TelemetryEntity(
        deviceId = deviceId ?: "",
        date = date ?: "",
        temperature = Temperature,
        air = Air,
        soil = Soil,
        light = Light,
        configVersion = configVersion
    )

fun IrrigationHistory.toEntity(): IrrigationHistoryEntity =
    IrrigationHistoryEntity(
        deviceId = deviceId ?: "",
        date = date ?: "",
        reason = reason,
        requestedDuration = requestedDuration,
        realDuration = realDuration,
        statusJson = status?.let { gson.toJson(it) },
        beforeJson = before?.let { gson.toJson(it) },
        afterJson = after?.let { gson.toJson(it) }
    )
