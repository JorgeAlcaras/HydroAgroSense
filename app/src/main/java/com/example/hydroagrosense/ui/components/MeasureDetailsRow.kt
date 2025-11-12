package com.example.hydroagrosense.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.hydroagrosense.utils.getImageForMeasure

@Composable
fun MeasureDetailsRow(
    modifier: Modifier = Modifier,
    measure: Measure,
    barMax: Double,
    barHeight: Dp = 20.dp,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        //Measure Image
        AsyncImage(
            model = getImageForMeasure(measure.type),
            contentDescription = measure.type,
            modifier = Modifier
                .padding(4.dp)
                .width(28.dp)
                .height(28.dp),
            alignment = Alignment.Center,
        )
        Spacer(modifier = Modifier.width(4.dp))
        //Measure Name
        Text(
            text = measure.type,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .width(115.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        //Optimal Range Bar
        MeasureBar(
            modifier = Modifier.weight(1f),
            measure = measure,
            barHeight = barHeight,
            barMax = barMax
        )
        Text(
            text = "${measure.value} ${measure.unit}",
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(65.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
