package com.example.hydroagrosense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.hydroagrosense.data.Irrigation
import com.example.hydroagrosense.data.Measure
import com.example.hydroagrosense.ui.components.IrrigationCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrrigationHistory() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDEDED),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Irrigation History",
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
            IrrigationCard(
                irrigation = Irrigation(
                    id = "1",
                    initialMeasure = Measure(
                    ),
                    date = "2024-06-01",
                )
            )
        }
    }
}