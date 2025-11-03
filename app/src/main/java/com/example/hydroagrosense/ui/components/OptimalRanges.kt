package com.example.hydroagrosense.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.hydroagrosense.data.OptimalMeasure
import com.example.hydroagrosense.utils.getBarMax


@Composable
fun OptimalRangesCard(
    modifier: Modifier = Modifier,
    optimalMeasures: List<OptimalMeasure>
) {
    val barHeight: Dp = 10.dp
    Card(
        modifier = modifier
            .padding(8.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF),
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        ),
        border = BorderStroke(color = Color(0xFFFFFFFF), width = 1.dp),)
     {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Text(
            text = "Optimal Ranges",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).align(Alignment.CenterHorizontally)
            )
        optimalMeasures.forEach { optimalMeasure ->
                OptimalRangeRow(
                    optimalMeasure = optimalMeasure,
                    barHeight = barHeight,
                    barMax = getBarMax(measureName = optimalMeasure.parameter),
                    modifier = Modifier
                        .weight(1f)
                )

        }
        }
     }
}




