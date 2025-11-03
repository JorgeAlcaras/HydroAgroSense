package com.example.hydroagrosense.data

data class OptimalMeasure(
    val id: Int,
    val parameter: String,
    val optimalMinValue: Double,
    val optimalMaxValue: Double,
    val unit: String
)