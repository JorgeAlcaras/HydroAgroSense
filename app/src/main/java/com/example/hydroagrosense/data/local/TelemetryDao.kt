package com.example.hydroagrosense.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hydroagrosense.data.local.entities.TelemetryEntity

@Dao
interface TelemetryDao {

    @Query(
        """
        SELECT * FROM telemetry 
        WHERE deviceId = :deviceId 
        ORDER BY date DESC 
        LIMIT :limit
        """
    )
    fun observeTelemetry(deviceId: String, limit: Int): LiveData<List<TelemetryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: List<TelemetryEntity>)
}
