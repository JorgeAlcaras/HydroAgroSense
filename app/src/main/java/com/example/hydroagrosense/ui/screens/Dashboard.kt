package com.example.hydroagrosense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.hydroagrosense.data.Measure
import com.example.hydroagrosense.data.OptimalMeasure
import com.example.hydroagrosense.ui.components.MeasureCard
import com.example.hydroagrosense.ui.components.OptimalRangesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDEDED),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "HydroAgroSense 🌱",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE3E3E3),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            OptimalRangesCard(
                optimalMeasures = listOf(
                    OptimalMeasure(
                        id = 1,
                        parameter = "Air Humidity",
                        optimalMinValue = 50.0,
                        optimalMaxValue = 70.0,
                        unit = "%",
                    ),
                    OptimalMeasure(
                        id = 2,
                        parameter = "Soil Moisture",
                        optimalMinValue = 40.0,
                        optimalMaxValue = 60.0,
                        unit = "%",
                    ),
                    OptimalMeasure(
                        id = 3,
                        parameter = "Temperature",
                        optimalMinValue = 20.0,
                        optimalMaxValue = 25.0,
                        unit = "°C",
                    ),
                    OptimalMeasure(
                        id = 4,
                        parameter = "Light Intensity",
                        optimalMinValue = 60.0,
                        optimalMaxValue = 100.0,
                        unit = "%",
                    )
                )
            )

            MeasureCard(
                measure = Measure(
                    id = 1,
                    type = "Air Humidity",
                    value = 60.0,
                    unit = "%",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = OptimalMeasure(
                        id = 1,
                        parameter = "Air Humidity",
                        optimalMinValue = 50.0,
                        optimalMaxValue = 70.0,
                        unit = "%",
                    )
                ),
            )
            MeasureCard(
                measure = Measure(
                    id = 2,
                    type = "Soil Moisture",
                    value = 45.0,
                    unit = "%",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = OptimalMeasure(
                        id = 2,
                        parameter = "Soil Moisture",
                        optimalMinValue = 40.0,
                        optimalMaxValue = 60.0,
                        unit = "%",
                    )
                )
            )
            MeasureCard(
                measure = Measure(
                    id = 3,
                    type = "Temperature",
                    value = 22.0,
                    unit = "°C",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = OptimalMeasure(
                        id = 3,
                        parameter = "Temperature",
                        optimalMinValue = 20.0,
                        optimalMaxValue = 25.0,
                        unit = "°C",
                    )
                )
            )
            MeasureCard(
                measure = Measure(
                    id = 4,
                    type = "Light Intensity",
                    value = 80.0,
                    unit = "%",
                    timestamp = System.currentTimeMillis(),
                    optimalMeasure = OptimalMeasure(
                        id = 4,
                        parameter = "Light Intensity",
                        optimalMinValue = 60.0,
                        optimalMaxValue = 100.0,
                        unit = "%",
                    )
                )
            )
        }


    }
}