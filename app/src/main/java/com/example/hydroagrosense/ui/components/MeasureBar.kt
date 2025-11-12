package com.example.hydroagrosense.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hydroagrosense.data.Measure

@Composable
fun MeasureBar(
    modifier: Modifier = Modifier,
    measure: Measure,
    barMax: Double,
    barHeight: Dp = 20.dp,
) {
    val levelColor = when {
        measure.value < measure.optimalMeasure.optimalMinValue -> Color(0xFF6C92C4) // Blue
        measure.value > measure.optimalMeasure.optimalMaxValue -> Color(0xFFC46C6C) // Red
        else -> Color(0xFF6CC472) // Green
    }

    Box(
        // Use incoming modifier so parent weight controls width; apply height here
        modifier = modifier
            .height(barHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFEDEDED))
        )

        val fraction =
            (measure.value.coerceAtLeast(0.0).coerceAtMost(barMax)).toFloat() / barMax
        val anim = animateFloatAsState(targetValue = fraction.toFloat())

        BoxWithConstraints {
            val fullPx = with(LocalDensity.current) { maxWidth.toPx() }
            val targetWidthPx = fullPx * anim.value
            Box(
                modifier = Modifier
                    .height(barHeight)
                    .clip(RoundedCornerShape(6.dp))
                    .width(with(LocalDensity.current) { targetWidthPx.toDp() })
                    .background(levelColor)
            )
        }
    }
}
