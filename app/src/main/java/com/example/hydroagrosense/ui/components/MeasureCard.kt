package com.example.hydroagrosense.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hydroagrosense.data.Measure
import com.example.hydroagrosense.utils.getBarMax
import com.example.hydroagrosense.utils.getImageForMeasure

@Composable
fun MeasureCard(
    modifier: Modifier = Modifier,
    measure: Measure,
) {
    val levelValue = when {
        measure.value < measure.optimalMeasure.optimalMinValue -> "Low"
        measure.value > measure.optimalMeasure.optimalMaxValue -> "High"
        else -> "Optimal"
    }

    val barHeight: Dp = 10.dp
    val barMax = getBarMax(measure.type)

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
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            // Header Row: measure type and icon
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = measure.type,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF000000)
                )

                AsyncImage(
                    model = getImageForMeasure(measure.type),
                    contentDescription = "Icon for ${measure.type}",
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Measure row with bar and value
            MeasureRow(
                modifier = Modifier.fillMaxWidth(),
                levelValue = levelValue,
                measure = measure,
                barMax = barMax,
                barHeight = barHeight
            )
        }
    }
}
