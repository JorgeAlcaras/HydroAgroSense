package com.example.hydroagrosense.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hydroagrosense.data.Irrigation

@Composable
fun IrrigationCard(
    modifier: Modifier,
    irrigation: Irrigation
) {

    Card(
        modifier = modifier
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

    }
}