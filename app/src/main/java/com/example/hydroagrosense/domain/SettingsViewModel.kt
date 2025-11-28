package com.example.hydroagrosense.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydroagrosense.domain.models.ConfigDto
import com.example.hydroagrosense.domain.models.IrrigationConfig
import com.example.hydroagrosense.domain.models.RangeConfig
import com.example.hydroagrosense.domain.models.SamplingConfig
import com.example.hydroagrosense.domain.models.SensorsConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SettingsUiState(
    val soilMoistureMin: String = "40",
    val soilMoistureMax: String = "60",
    val airHumidityMin: String = "50",
    val airHumidityMax: String = "70",
    val temperatureMin: String = "20",
    val temperatureMax: String = "30",
    val lightIntensityMin: String = "70",
    val lightIntensityMax: String = "100",
    val autoEnabled: Boolean = true,
    val minOnSeconds: String = "10",
    val maxOnSeconds: String = "30",
    val manualDefaultSeconds: String = "20",
    val cooldownSeconds: String = "60",
    val readIntervalSeconds: String = "10",
    val sendIntervalSeconds: String = "30",
    val configRefreshSeconds: String = "30"
)

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    private val apiService = ApiClient.api

    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun updateSoilMoistureMin(value: String) {
        _uiState.value = _uiState.value.copy(soilMoistureMin = value)
    }

    fun updateSoilMoistureMax(value: String) {
        _uiState.value = _uiState.value.copy(soilMoistureMax = value)
    }

    fun updateAirHumidityMin(value: String) {
        _uiState.value = _uiState.value.copy(airHumidityMin = value)
    }

    fun updateAirHumidityMax(value: String) {
        _uiState.value = _uiState.value.copy(airHumidityMax = value)
    }

    fun updateTemperatureMin(value: String) {
        _uiState.value = _uiState.value.copy(temperatureMin = value)
    }

    fun updateTemperatureMax(value: String) {
        _uiState.value = _uiState.value.copy(temperatureMax = value)
    }

    fun updateLightIntensityMin(value: String) {
        _uiState.value = _uiState.value.copy(lightIntensityMin = value)
    }

    fun updateLightIntensityMax(value: String) {
        _uiState.value = _uiState.value.copy(lightIntensityMax = value)
    }

    fun updateAutoEnabled(value: Boolean) {
        _uiState.value = _uiState.value.copy(autoEnabled = value)
    }

    fun updateMinOnSeconds(value: String) {
        _uiState.value = _uiState.value.copy(minOnSeconds = value)
    }

    fun updateMaxOnSeconds(value: String) {
        _uiState.value = _uiState.value.copy(maxOnSeconds = value)
    }

    fun updateManualDefaultSeconds(value: String) {
        _uiState.value = _uiState.value.copy(manualDefaultSeconds = value)
    }

    fun updateCooldownSeconds(value: String) {
        _uiState.value = _uiState.value.copy(cooldownSeconds = value)
    }

    fun updateReadIntervalSeconds(value: String) {
        _uiState.value = _uiState.value.copy(readIntervalSeconds = value)
    }

    fun updateSendIntervalSeconds(value: String) {
        _uiState.value = _uiState.value.copy(sendIntervalSeconds = value)
    }

    fun updateConfigRefreshSeconds(value: String) {
        _uiState.value = _uiState.value.copy(configRefreshSeconds = value)
    }

    fun saveConfiguration() {
        viewModelScope.launch {
            val configDto = buildConfigDto()
            apiService.setConfig("raspi-01", configDto)
        }
    }

    private fun buildConfigDto(): ConfigDto {
        val state = _uiState.value
        return ConfigDto(
            deviceId = "raspi-01",
            version = 1,
            lastUpdated = java.time.Instant.now().toString(),
            sensors = SensorsConfig(
                soilMoisture = RangeConfig(
                    min = state.soilMoistureMin.toIntOrNull() ?: 40,
                    max = state.soilMoistureMax.toIntOrNull() ?: 60
                ),
                airHumidity = RangeConfig(
                    min = state.airHumidityMin.toIntOrNull() ?: 50,
                    max = state.airHumidityMax.toIntOrNull() ?: 70
                ),
                temperature = RangeConfig(
                    min = state.temperatureMin.toIntOrNull() ?: 20,
                    max = state.temperatureMax.toIntOrNull() ?: 30
                ),
                lightIntensity = RangeConfig(
                    min = state.lightIntensityMin.toIntOrNull() ?: 70,
                    max = state.lightIntensityMax.toIntOrNull() ?: 100
                )
            ),
            irrigation = IrrigationConfig(
                autoEnabled = state.autoEnabled,
                mode = if (state.autoEnabled) "auto" else "manual",
                minOnSeconds = state.minOnSeconds.toIntOrNull() ?: 10,
                maxOnSeconds = state.maxOnSeconds.toIntOrNull() ?: 30,
                manualDefaultSeconds = state.manualDefaultSeconds.toIntOrNull() ?: 20,
                cooldownSeconds = state.cooldownSeconds.toIntOrNull() ?: 60
            ),
            sampling = SamplingConfig(
                readIntervalSeconds = state.readIntervalSeconds.toIntOrNull() ?: 10,
                sendIntervalSeconds = state.sendIntervalSeconds.toIntOrNull() ?: 30,
                configRefreshSeconds = state.configRefreshSeconds.toIntOrNull() ?: 30
            )
        )
    }
}
