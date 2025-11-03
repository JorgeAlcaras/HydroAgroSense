package com.example.hydroagrosense.data

import kotlin.time.Duration

data class Irrigation(
    val id: String,
    val initialMeasure: List<Measure>,
    val finalMeasure: List<Measure>,
    val duration: Duration,
    val date: String
)