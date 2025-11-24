package com.example.hydroagrosense.domain.models

data class SamplingConfig(
    val readIntervalSeconds: Int,
    val sendIntervalSeconds: Int,
    val configRefreshSeconds: Int
)