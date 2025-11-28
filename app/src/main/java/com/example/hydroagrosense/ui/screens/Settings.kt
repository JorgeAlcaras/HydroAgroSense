package com.example.hydroagrosense.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hydroagrosense.domain.HydroViewModel
import com.example.hydroagrosense.domain.models.ConfigDto
import com.example.hydroagrosense.domain.models.IrrigationConfig
import com.example.hydroagrosense.domain.models.RangeConfig
import com.example.hydroagrosense.domain.models.SamplingConfig
import com.example.hydroagrosense.domain.models.SensorsConfig
import com.example.hydroagrosense.ui.components.SensorRangeCard
import toDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: HydroViewModel = viewModel()
) {
    // LiveData<ConfigEntity?>
    val configEntity = viewModel.config.observeAsState().value
    // Lo convertimos a DTO si existe
    val configDto = configEntity?.toDto()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDEDED),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE3E3E3),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        }
    ) { padding ->

        // Mientras no haya config cargada, mostramos un loading
        if (configDto == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        // ----------------- ESTADO LOCAL DE LOS CAMPOS -----------------

        // Sensores: Soil Moisture
        var soilMoistureMinText by rememberSaveable {
            mutableStateOf(configDto.sensors.soilMoisture.min.toString())
        }
        var soilMoistureMaxText by rememberSaveable {
            mutableStateOf(configDto.sensors.soilMoisture.max.toString())
        }

        // Sensores: Air Humidity
        var airHumidityMinText by rememberSaveable {
            mutableStateOf(configDto.sensors.airHumidity.min.toString())
        }
        var airHumidityMaxText by rememberSaveable {
            mutableStateOf(configDto.sensors.airHumidity.max.toString())
        }

        // Sensores: Temperature
        var temperatureMinText by rememberSaveable {
            mutableStateOf(configDto.sensors.temperature.min.toString())
        }
        var temperatureMaxText by rememberSaveable {
            mutableStateOf(configDto.sensors.temperature.max.toString())
        }

        // Sensores: Light Intensity
        var lightIntensityMinText by rememberSaveable {
            mutableStateOf(configDto.sensors.lightIntensity.min.toString())
        }
        var lightIntensityMaxText by rememberSaveable {
            mutableStateOf(configDto.sensors.lightIntensity.max.toString())
        }

        // Irrigation
        var autoEnabled by rememberSaveable {
            mutableStateOf(configDto.irrigation.autoEnabled)
        }
        var minOnSecondsText by rememberSaveable {
            mutableStateOf(configDto.irrigation.minOnSeconds.toString())
        }
        var maxOnSecondsText by rememberSaveable {
            mutableStateOf(configDto.irrigation.maxOnSeconds.toString())
        }
        var manualDefaultSecondsText by rememberSaveable {
            mutableStateOf(configDto.irrigation.manualDefaultSeconds.toString())
        }
        var cooldownSecondsText by rememberSaveable {
            mutableStateOf(configDto.irrigation.cooldownSeconds.toString())
        }

        // Sampling
        var readIntervalSecondsText by rememberSaveable {
            mutableStateOf(configDto.sampling.readIntervalSeconds.toString())
        }
        var sendIntervalSecondsText by rememberSaveable {
            mutableStateOf(configDto.sampling.sendIntervalSeconds.toString())
        }
        var configRefreshSecondsText by rememberSaveable {
            mutableStateOf(configDto.sampling.configRefreshSeconds.toString())
        }

        // ----------------- UI -----------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Sensores
            Text("Sensors", style = MaterialTheme.typography.titleMedium)

            SensorRangeCard(
                label = "Soil Moisture (%)",
                min = soilMoistureMinText,
                max = soilMoistureMaxText,
                onMinChange = { soilMoistureMinText = it },
                onMaxChange = { soilMoistureMaxText = it }
            )

            SensorRangeCard(
                label = "Air Humidity (%)",
                min = airHumidityMinText,
                max = airHumidityMaxText,
                onMinChange = { airHumidityMinText = it },
                onMaxChange = { airHumidityMaxText = it }
            )

            SensorRangeCard(
                label = "Temperature (°C)",
                min = temperatureMinText,
                max = temperatureMaxText,
                onMinChange = { temperatureMinText = it },
                onMaxChange = { temperatureMaxText = it }
            )

            SensorRangeCard(
                label = "Light Intensity (%)",
                min = lightIntensityMinText,
                max = lightIntensityMaxText,
                onMinChange = { lightIntensityMinText = it },
                onMaxChange = { lightIntensityMaxText = it }
            )

            Divider()

            // Riego
            Text("Irrigation", style = MaterialTheme.typography.titleMedium)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Automatic")
                Switch(
                    checked = autoEnabled,
                    onCheckedChange = { autoEnabled = it }
                )
            }

            OutlinedTextField(
                value = minOnSecondsText,
                onValueChange = { minOnSecondsText = it },
                label = { Text("Min. seconds on") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = maxOnSecondsText,
                onValueChange = { maxOnSecondsText = it },
                label = { Text("Max. seconds on") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = manualDefaultSecondsText,
                onValueChange = { manualDefaultSecondsText = it },
                label = { Text("Default seconds (manual)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cooldownSecondsText,
                onValueChange = { cooldownSecondsText = it },
                label = { Text("Waiting seconds") },
                modifier = Modifier.fillMaxWidth()
            )

            Divider()

            // Muestreo
            Text("Muestreo", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = readIntervalSecondsText,
                onValueChange = { readIntervalSecondsText = it },
                label = { Text("Reading interval (s)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = sendIntervalSecondsText,
                onValueChange = { sendIntervalSecondsText = it },
                label = { Text("Shipping interval(s)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = configRefreshSecondsText,
                onValueChange = { configRefreshSecondsText = it },
                label = { Text("Refresh configuration(s)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // Parsear a Int con fallback a los valores actuales
                    val updated = ConfigDto(
                        deviceId = configDto.deviceId,
                        version = configDto.version,        // o incrementa si así lo manejas
                        lastUpdated = configDto.lastUpdated, // el backend lo puede sobreescribir
                        sensors = SensorsConfig(
                            soilMoisture = RangeConfig(
                                min = soilMoistureMinText.toIntOrNull()
                                    ?: configDto.sensors.soilMoisture.min,
                                max = soilMoistureMaxText.toIntOrNull()
                                    ?: configDto.sensors.soilMoisture.max
                            ),
                            airHumidity = RangeConfig(
                                min = airHumidityMinText.toIntOrNull()
                                    ?: configDto.sensors.airHumidity.min,
                                max = airHumidityMaxText.toIntOrNull()
                                    ?: configDto.sensors.airHumidity.max
                            ),
                            temperature = RangeConfig(
                                min = temperatureMinText.toIntOrNull()
                                    ?: configDto.sensors.temperature.min,
                                max = temperatureMaxText.toIntOrNull()
                                    ?: configDto.sensors.temperature.max
                            ),
                            lightIntensity = RangeConfig(
                                min = lightIntensityMinText.toIntOrNull()
                                    ?: configDto.sensors.lightIntensity.min,
                                max = lightIntensityMaxText.toIntOrNull()
                                    ?: configDto.sensors.lightIntensity.max
                            )
                        ),
                        irrigation = IrrigationConfig(
                            autoEnabled = autoEnabled,
                            cooldownSeconds = cooldownSecondsText.toIntOrNull()
                                ?: configDto.irrigation.cooldownSeconds,
                            manualDefaultSeconds = manualDefaultSecondsText.toIntOrNull()
                                ?: configDto.irrigation.manualDefaultSeconds,
                            maxOnSeconds = maxOnSecondsText.toIntOrNull()
                                ?: configDto.irrigation.maxOnSeconds,
                            minOnSeconds = minOnSecondsText.toIntOrNull()
                                ?: configDto.irrigation.minOnSeconds,
                            mode = configDto.irrigation.mode // lo mantenemos igual
                        ),
                        sampling = SamplingConfig(
                            configRefreshSeconds = configRefreshSecondsText.toIntOrNull()
                                ?: configDto.sampling.configRefreshSeconds,
                            readIntervalSeconds = readIntervalSecondsText.toIntOrNull()
                                ?: configDto.sampling.readIntervalSeconds,
                            sendIntervalSeconds = sendIntervalSecondsText.toIntOrNull()
                                ?: configDto.sampling.sendIntervalSeconds
                        )
                    )

                    viewModel.saveConfig(updated)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save and Send")
            }
        }
    }
}