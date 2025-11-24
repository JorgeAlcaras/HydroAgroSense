package com.example.hydroagrosense.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hydroagrosense.data.local.HydroDatabase
import com.example.hydroagrosense.data.local.entities.IrrigationHistoryEntity
import com.example.hydroagrosense.domain.models.ConfigDto
import kotlinx.coroutines.launch

class HydroViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HydroDatabase.getInstance(application)

    private val repository = HydroRepository(
        api = ApiClient.api,
        configDao = db.configDao(),
        telemetryDao = db.telemetryDao(),
        irrigationHistoryDao = db.irrigationHistoryDao()
    )

    private val deviceId = "raspi-01"

    // LiveData que vienen de Room (SQLite)
    val config = repository.observeConfig(deviceId)
    val telemetry = repository.observeTelemetry(deviceId, limit = 50)
    val irrigationHistory = repository.observeIrrigationHistory(deviceId, limit = 20)

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun syncAll() {
        viewModelScope.launch {
            try {
                repository.refreshConfig(deviceId)
                repository.refreshTelemetry(deviceId)
                repository.refreshIrrigationHistory(deviceId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error sincronizando: ${e.message}"
            }
        }
    }

    fun saveConfig(updated: ConfigDto) {
        viewModelScope.launch {
            try {
                repository.updateConfig(deviceId, updated)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error guardando config: ${e.message}"
            }
        }
    }

    // Importa MutableLiveData / LiveData si no los tienes ya
    private val _selectedHistory = MutableLiveData<IrrigationHistoryEntity?>()
    val selectedHistory: LiveData<IrrigationHistoryEntity?> = _selectedHistory

    fun selectHistoryItem(item: IrrigationHistoryEntity) {
        _selectedHistory.value = item
    }

}


