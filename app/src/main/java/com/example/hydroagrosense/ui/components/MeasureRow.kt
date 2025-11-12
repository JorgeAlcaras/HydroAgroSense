package com.example.hydroagrosense.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.weight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydroagrosense.data.Measure

@Composable
fun MeasureRow(
    levelValue: String,
    measure: Measure,
    modifier: Modifier = Modifier,
    barMax: Double,
    barHeight: Dp = 20.dp,
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Level Text
        Text(
            text = levelValue,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color(0xFF000000),
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Measure Bar: use a fresh Modifier.weight so it takes remaining space
        MeasureBar(
            modifier = Modifier.weight(1f),
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
            color = Color(0xFF000000),
            maxLines = 1,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
