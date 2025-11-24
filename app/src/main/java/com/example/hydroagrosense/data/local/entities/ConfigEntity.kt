package com.example.hydroagrosense.data.local.entities

import androidx.room.*

@Entity(tableName = "config")
data class ConfigEntity(
    @PrimaryKey val deviceId: String,
    val version: Int,
    val lastUpdated: String?,
    val soilMin: Int,
    val soilMax: Int,
    val airMin: Int,
    val airMax: Int,
    val tempMin: Int,
    val tempMax: Int,
    val lightMin: Int,
    val lightMax: Int,
    val autoEnabled: Boolean,
    val mode: String,
    val minOnSeconds: Int,
    val maxOnSeconds: Int,
    val manualDefaultSeconds: Int,
    val cooldownSeconds: Int,
    val readIntervalSeconds: Int,
    val sendIntervalSeconds: Int,
    val configRefreshSeconds: Int
)