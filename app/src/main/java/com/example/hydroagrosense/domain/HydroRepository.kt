package com.example.hydroagrosense.domain

import androidx.lifecycle.LiveData
import com.example.hydroagrosense.data.local.ConfigDao
import com.example.hydroagrosense.data.local.IrrigationHistoryDao
import com.example.hydroagrosense.data.local.TelemetryDao
import com.example.hydroagrosense.data.local.entities.ConfigEntity
import com.example.hydroagrosense.data.local.entities.IrrigationHistoryEntity
import com.example.hydroagrosense.data.local.entities.TelemetryEntity
import com.example.hydroagrosense.data.toEntity
import com.example.hydroagrosense.domain.models.ConfigDto

class HydroRepository(
    private val api: ApiService,
    private val configDao: ConfigDao,
    private val telemetryDao: TelemetryDao,
    private val irrigationHistoryDao: IrrigationHistoryDao
) {

    // ------- CONFIG -------

    fun observeConfig(deviceId: String): LiveData<ConfigEntity?> {
        return configDao.observeConfig(deviceId)
    }

    suspend fun refreshConfig(deviceId: String) {
        val cfg = api.getConfig(deviceId)
        configDao.upsert(cfg.toEntity())
    }

    suspend fun updateConfig(deviceId: String, newConfig: ConfigDto) {
        api.setConfig(deviceId, newConfig)
        configDao.upsert(newConfig.toEntity())
    }

    // ------- TELEMETRY -------

    fun observeTelemetry(deviceId: String, limit: Int): LiveData<List<TelemetryEntity>> {
        return telemetryDao.observeTelemetry(deviceId, limit)
    }

    suspend fun refreshTelemetry(deviceId: String, limit: Int = 50) {
        val list = api.getTelemetry(deviceId, limit)
        telemetryDao.insertAll(list.map { it.toEntity() })
    }

    // ------- IRRIGATION HISTORY -------

    fun observeIrrigationHistory(
        deviceId: String,
        limit: Int
    ): LiveData<List<IrrigationHistoryEntity>> {
        return irrigationHistoryDao.observeHistory(deviceId, limit)
    }

    suspend fun refreshIrrigationHistory(deviceId: String, limit: Int = 20) {
        val list = api.getIrrigationHistory(deviceId, limit)
        irrigationHistoryDao.deleteAll(deviceId) // Limpia primero
        irrigationHistoryDao.insertAll(list.map { it.toEntity() })
    }

}