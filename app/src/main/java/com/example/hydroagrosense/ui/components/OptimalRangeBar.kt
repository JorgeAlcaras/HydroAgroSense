package com.example.hydroagrosense.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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


@Composable
fun OptimalRangeBar(
    modifier: Modifier = Modifier,
    optimalMinValue: Double,
    optimalMaxValue: Double,
    barMax: Double,
    barHeight: Dp = 20.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(barHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFEDEDED))
        )

        val optimalMinFraction = optimalMinValue.toFloat() / barMax
        val optimalMaxFraction = optimalMaxValue.toFloat() / barMax

        val animMin = animateFloatAsState(targetValue = optimalMinFraction.toFloat())
        val animMax = animateFloatAsState(targetValue = optimalMaxFraction.toFloat())

        BoxWithConstraints {
            val fullPx = with(LocalDensity.current) { maxWidth.toPx() }
            val startPx = fullPx * animMin.value
            val endPx = fullPx * animMax.value
            val widthPx = endPx - startPx

            Box(
                modifier = Modifier
                    .height(barHeight)
                    .offset(x = with(LocalDensity.current) { startPx.toDp() }) // posición inicial
                    .width(with(LocalDensity.current) { widthPx.toDp() }) // ancho solo del rango óptimo
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF6CC472))
            )
        }
    }
}
