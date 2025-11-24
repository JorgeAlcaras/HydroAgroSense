package com.example.hydroagrosense.data.local.entities

import androidx.room.*

@Entity(
    tableName = "irrigation_history",
    indices = [Index(value = ["deviceId", "date"])]
)
data class IrrigationHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceId: String,
    val date: String,
    val reason: String?,
    val requestedDuration: Int?,
    val realDuration: Int?,
    val statusJson: String?,      // guardamos el status como JSON
    val beforeJson: String?,      // telemetryBefore como JSON
    val afterJson: String?        // telemetryAfter como JSON
)