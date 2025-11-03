// Kotlin
package com.example.hydroagrosense.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hydroagrosense.data.OptimalMeasure
import com.example.hydroagrosense.utils.getImageForMeasure

@Composable
fun OptimalRangeRow(
    modifier: Modifier = Modifier,
    optimalMeasure: OptimalMeasure,
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
            model = getImageForMeasure(optimalMeasure.parameter),
            contentDescription = optimalMeasure.parameter,
            modifier = Modifier
                .padding(4.dp).width(28.dp).height(28.dp),
            alignment = Alignment.Center,
        )
        Spacer(modifier = Modifier.width(4.dp))
        //Measure Name
        Text(
            text = optimalMeasure.parameter,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.width(115.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        //Optimal Range Bar
        OptimalRangeBar(
            optimalMinValue = optimalMeasure.optimalMinValue,
            optimalMaxValue = optimalMeasure.optimalMaxValue,
            barMax = barMax,
            barHeight = barHeight,
            modifier = Modifier.weight(1f)
        )
        //Optimal Range Text
        Text(
            text = "${optimalMeasure.optimalMinValue.toInt()}-${optimalMeasure.optimalMaxValue.toInt()}${optimalMeasure.unit}",
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 8.dp).width(65.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
