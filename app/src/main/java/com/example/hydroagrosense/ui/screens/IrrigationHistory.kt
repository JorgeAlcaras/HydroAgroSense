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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hydroagrosense.domain.HydroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrrigationHistory(
    navController: NavHostController,
    viewModel: HydroViewModel
) {
    val historyList = viewModel.irrigationHistory.observeAsState(emptyList()).value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDEDED),
        topBar = { /* igual que ya lo tienes */ }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            historyList.forEach { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    onClick = {
                        // 👉 aquí “pasamos” el item al details
                        viewModel.selectHistoryItem(item)
                        navController.navigate("details")
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color.Black,
                    ),
                    border = BorderStroke(color = Color(0xFFFFFFFF), width = 1.dp),
                ) {
                    Text(
                        text = "Irrigation ${item.date}",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Duration: ${item.realDuration ?: item.requestedDuration ?: 0} s",
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
                    )
                    Text(
                        text = "Reason: ${item.reason ?: "N/A"}",
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}
