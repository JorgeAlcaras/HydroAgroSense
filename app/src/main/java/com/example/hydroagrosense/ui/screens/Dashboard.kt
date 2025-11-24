package com.example.hydroagrosense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.hydroagrosense.data.Measure
import com.example.hydroagrosense.data.OptimalMeasure
import com.example.hydroagrosense.domain.HydroViewModel
import com.example.hydroagrosense.ui.components.MeasureCard
import com.example.hydroagrosense.ui.components.OptimalRangesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    navController: NavHostController,
    viewModel: HydroViewModel
) {
    val scrollState = rememberScrollState()

    // import androidx.compose.runtime.livedata.observeAsState
    val configEntity = viewModel.config.observeAsState().value
    val telemetryList = viewModel.telemetry.observeAsState(emptyList()).value

    // Disparar syncAll cuando entra a la pantalla
    LaunchedEffect(Unit) {
        viewModel.syncAll()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDEDED),
        topBar = { /* ... igual que lo tienes ... */ }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {

            // Si ya hay config en la DB, usarla para los rangos
            val optimalMeasures = if (configEntity != null) {
                listOf(
                    OptimalMeasure(
                        id = 1,
                        parameter = "Air Humidity",
                        optimalMinValue = configEntity.airMin.toDouble(),
                        optimalMaxValue = configEntity.airMax.toDouble(),
                        unit = "%"
                    ),
                    OptimalMeasure(
                        id = 2,
                        parameter = "Soil Moisture",
                        optimalMinValue = configEntity.soilMin.toDouble(),
                        optimalMaxValue = configEntity.soilMax.toDouble(),
                        unit = "%"
                    ),
                    OptimalMeasure(
                        id = 3,
                        parameter = "Temperature",
                        optimalMinValue = configEntity.tempMin.toDouble(),
                        optimalMaxValue = configEntity.tempMax.toDouble(),
                        unit = "°C"
                    ),
                    OptimalMeasure(
                        id = 4,
                        parameter = "Light Intensity",
                        optimalMinValue = configEntity.lightMin.toDouble(),
                        optimalMaxValue = configEntity.lightMax.toDouble(),
                        unit = "%"
                    )
                )
            } else {
                // fallback a los valores quemados mientras no haya config
                listOf(
                    OptimalMeasure(1, "Air Humidity", 50.0, 70.0, "%"),
                    OptimalMeasure(2, "Soil Moisture", 40.0, 60.0, "%"),
                    OptimalMeasure(3, "Temperature", 20.0, 25.0, "°C"),
                    OptimalMeasure(4, "Light Intensity", 60.0, 100.0, "%")
                )
            }

            OptimalRangesCard(optimalMeasures = optimalMeasures)

            // Tomar la última telemetría guardada
            val lastTelemetry = telemetryList.firstOrNull()

            // Si no hay datos aún, puedes mostrar placeholders
            MeasureCard(
                measure = Measure(
                    id = 1,
                    type = "Air Humidity",
                    value = lastTelemetry?.air ?: 0.0,
                    unit = "%",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = optimalMeasures[0]
                )
            )
            MeasureCard(
                measure = Measure(
                    id = 2,
                    type = "Soil Moisture",
                    value = lastTelemetry?.soil ?: 0.0,
                    unit = "%",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = optimalMeasures[1]
                )
            )
            MeasureCard(
                measure = Measure(
                    id = 3,
                    type = "Temperature",
                    value = lastTelemetry?.temperature ?: 0.0,
                    unit = "°C",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = optimalMeasures[2]
                )
            )
            MeasureCard(
                measure = Measure(
                    id = 4,
                    type = "Light Intensity",
                    value = lastTelemetry?.light ?: 0.0,
                    unit = "%",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = optimalMeasures[3]
                )
            )
        }
    }
}
