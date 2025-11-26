package com.example.hydroagrosense.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "telemetry",
    indices = [Index(value = ["deviceId", "date"])]
)
data class TelemetryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceId: String,
    val date: String,
    val Temperature: Double?,
    val Air: Double?,
    val Soil: Double?,
    val Light: Double?,
    val configVersion: Int?
)