package com.example.hydroagrosense.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hydroagrosense.data.Measure
import com.example.hydroagrosense.data.OptimalMeasure
import com.example.hydroagrosense.domain.HydroViewModel
import com.example.hydroagrosense.ui.components.MeasureDetailsRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrrigationDetails(
    navController: NavHostController?,
    viewModel: HydroViewModel
) {
    // Obtenemos el item seleccionado desde el ViewModel
    val history = viewModel.selectedHistory.observeAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDEDED),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Irrigation Details",
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
            modifier = Modifier.padding(innerPadding)
        ) {

            // --------- CARD: IRRIGATION EVENT (usa el item real si existe) ----------
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                ),
                border = BorderStroke(color = Color(0xFFFFFFFF), width = 1.dp),
            ) {
                Text(
                    text = "Irrigation Event",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Date: ${history?.date ?: "N/A"}",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
                )
                Text(
                    text = "Duration: ${(history?.realDuration ?: history?.requestedDuration ?: 0)} s",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
                )
                Text(
                    text = "Reason: ${history?.reason ?: "Unknown"}",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ---------------------- BEFORE IRRIGATION ----------------------
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                ),
                border = BorderStroke(color = Color(0xFFFFFFFF), width = 1.dp),
            ) {
                Text(
                    text = "Before Irrigation",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 100.0,
                    measure = Measure(
                        id = 2,
                        type = "Soil Moisture",
                        value = 32.0,
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
                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 100.0,
                    measure = Measure(
                        id = 1,
                        type = "Air Humidity",
                        value = 35.0,
                        unit = "%",
                        timestamp = System.currentTimeMillis(),
                        optimalMeasure = OptimalMeasure(
                            id = 1,
                            parameter = "Air Humidity",
                            optimalMinValue = 50.0,
                            optimalMaxValue = 70.0,
                            unit = "%",
                        )
                    )

                )
                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 50.0,
                    measure = Measure(
                        id = 3,
                        type = "Temperature",
                        value = 37.0,
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
                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 100.0,
                    measure = Measure(
                        id = 4,
                        type = "Light Intensity",
                        value = 85.0,
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

                Spacer(modifier = Modifier.height(16.dp))
            }

            // ---------------------- AFTER IRRIGATION ----------------------
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                ),
                border = BorderStroke(color = Color(0xFFFFFFFF), width = 1.dp),
            ) {
                Text(
                    text = "After Irrigation",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 100.0,
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
                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 100.0,
                    measure = Measure(
                        id = 1,
                        type = "Air Humidity",
                        value = 55.0,
                        unit = "%",
                        timestamp = System.currentTimeMillis(),
                        optimalMeasure = OptimalMeasure(
                            id = 1,
                            parameter = "Air Humidity",
                            optimalMinValue = 50.0,
                            optimalMaxValue = 70.0,
                            unit = "%",
                        )
                    )

                )
                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 50.0,
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
                MeasureDetailsRow(
                    modifier = Modifier.padding(vertical = 8.dp),
                    barMax = 100.0,
                    measure = Measure(
                        id = 4,
                        type = "Light Intensity",
                        value = 70.0,
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
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
