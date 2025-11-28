package com.example.hydroagrosense.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hydroagrosense.domain.SettingsViewModel
import com.example.hydroagrosense.ui.components.SensorRangeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
                    navController.let { nav ->
                        IconButton(onClick = { nav.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
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
                "Soil Moisture (%)",
                uiState.soilMoistureMin, uiState.soilMoistureMax,
                { viewModel.updateSoilMoistureMin(it) },
                { viewModel.updateSoilMoistureMax(it) }
            )

            SensorRangeCard(
                "Air Humidity (%)",
                uiState.airHumidityMin, uiState.airHumidityMax,
                { viewModel.updateAirHumidityMin(it) },
                { viewModel.updateAirHumidityMax(it) }
            )

            SensorRangeCard(
                "Temperature (°C)",
                uiState.temperatureMin, uiState.temperatureMax,
                { viewModel.updateTemperatureMin(it) },
                { viewModel.updateTemperatureMax(it) }
            )

            SensorRangeCard(
                "Light Intensity (%)",
                uiState.lightIntensityMin, uiState.lightIntensityMax,
                { viewModel.updateLightIntensityMin(it) },
                { viewModel.updateLightIntensityMax(it) }
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
                    checked = uiState.autoEnabled,
                    onCheckedChange = { viewModel.updateAutoEnabled(it) }
                )
            }
            OutlinedTextField(
                value = uiState.minOnSeconds,
                onValueChange = { viewModel.updateMinOnSeconds(it) },
                label = { Text("Min. seconds on") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = uiState.maxOnSeconds,
                onValueChange = { viewModel.updateMaxOnSeconds(it) },
                label = { Text("Max. seconds on") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.manualDefaultSeconds,
                onValueChange = { viewModel.updateManualDefaultSeconds(it) },
                label = { Text("Default seconds (manual)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.cooldownSeconds,
                onValueChange = { viewModel.updateCooldownSeconds(it) },
                label = { Text("Waiting seconds") },
                modifier = Modifier.fillMaxWidth()
            )

            Divider()

            // Muestreo
            Text("Muestreo", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = uiState.readIntervalSeconds,
                onValueChange = { viewModel.updateReadIntervalSeconds(it) },
                label = { Text("Reading interval (s)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.sendIntervalSeconds,
                onValueChange = { viewModel.updateSendIntervalSeconds(it) },
                label = { Text("Shipping interval(s)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.configRefreshSeconds,
                onValueChange = { viewModel.updateConfigRefreshSeconds(it) },
                label = { Text("Refresh configuration(s)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.saveConfiguration()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save and Send")
            }
        }
    }
}