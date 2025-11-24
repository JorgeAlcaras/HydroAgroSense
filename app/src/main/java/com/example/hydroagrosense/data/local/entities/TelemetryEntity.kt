package com.example.hydroagrosense.data.local.entities

import androidx.room.*

@Entity(
    tableName = "telemetry",
    indices = [Index(value = ["deviceId", "date"])]
)
data class TelemetryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceId: String,
    val date: String,
    val temperature: Double?,
    val air: Double?,
    val soil: Double?,
    val light: Double?,
    val configVersion: Int?
)