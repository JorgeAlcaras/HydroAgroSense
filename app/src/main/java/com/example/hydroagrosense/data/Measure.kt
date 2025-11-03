package com.example.hydroagrosense.data

data class Measure(
    val id: Int,
    val type: String,
    val value: Double,
    val unit: String,
    val timestamp: Long,
    val optimalMeasure: OptimalMeasure
)