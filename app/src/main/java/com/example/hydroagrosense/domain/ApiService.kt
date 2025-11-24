package com.example.hydroagrosense.domain

import com.example.hydroagrosense.domain.models.*
import retrofit2.http.*

interface ApiService {

    // GET /api/config/{deviceId}
    @GET("api/config/{deviceId}")
    suspend fun getConfig(
        @Path("deviceId") deviceId: String
    ): ConfigDto

    // POST /api/config/{deviceId}
    @POST("api/config/{deviceId}")
    suspend fun setConfig(
        @Path("deviceId") deviceId: String,
        @Body config: ConfigDto
    ): SetConfigResponse

    // GET /api/telemetry?deviceId=raspi-01&limit=50
    @GET("api/telemetry")
    suspend fun getTelemetry(
        @Query("deviceId") deviceId: String,
        @Query("limit") limit: Int = 50
    ): List<Telemetry>

    // GET /api/irrigation-history?deviceId=raspi-01&limit=20
    @GET("api/irrigation-history")
    suspend fun getIrrigationHistory(
        @Query("deviceId") deviceId: String,
        @Query("limit") limit: Int = 20
    ): List<IrrigationHistory>
}

// Respuesta simple del SetConfig (la que pusimos en la Function)
data class SetConfigResponse(
    val status: String,
    val deviceId: String,
    val message: String
)
