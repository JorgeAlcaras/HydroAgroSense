package com.example.hydroagrosense.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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

@SuppressLint("UnusedBoxWithConstraintsScope")
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
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Measure Type Text
                Text(
                    text = measure.type,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF000000)
                )
                // Measure Icon
                AsyncImage(
                    model = getImageForMeasure(measure.type),
                    contentDescription = "Icon for ${measure.type}",
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            // Level Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.width(68.dp)
                ) {
                    // Level Text
                    Text(
                        text = levelValue,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(0xFF000000)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Measure Bar
                MeasureBar(
                    measure = measure,
                    barHeight = barHeight,
                    barMax = barMax
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Value Text
                Text(
                    text = "${measure.value} ${measure.unit}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color(0xFF000000)
                )

            }
        }
    }
}
