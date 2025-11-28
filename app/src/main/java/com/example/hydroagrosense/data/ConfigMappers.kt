import com.example.hydroagrosense.data.local.entities.ConfigEntity
import com.example.hydroagrosense.domain.models.ConfigDto
import com.example.hydroagrosense.domain.models.IrrigationConfig
import com.example.hydroagrosense.domain.models.RangeConfig
import com.example.hydroagrosense.domain.models.SamplingConfig
import com.example.hydroagrosense.domain.models.SensorsConfig

fun ConfigEntity.toDto(): ConfigDto {
    return ConfigDto(
        deviceId = deviceId,
        version = version,
        lastUpdated = lastUpdated,
        sensors = SensorsConfig(
            soilMoisture = RangeConfig(min = soilMin, max = soilMax),
            airHumidity = RangeConfig(min = airMin, max = airMax),
            temperature = RangeConfig(min = tempMin, max = tempMax),
            lightIntensity = RangeConfig(min = lightMin, max = lightMax)
        ),
        irrigation = IrrigationConfig(
            autoEnabled = autoEnabled,
            cooldownSeconds = cooldownSeconds,
            manualDefaultSeconds = manualDefaultSeconds,
            maxOnSeconds = maxOnSeconds,
            minOnSeconds = minOnSeconds,
            mode = mode
        ),
        sampling = SamplingConfig(
            configRefreshSeconds = configRefreshSeconds,
            readIntervalSeconds = readIntervalSeconds,
            sendIntervalSeconds = sendIntervalSeconds
        )
    )
}
