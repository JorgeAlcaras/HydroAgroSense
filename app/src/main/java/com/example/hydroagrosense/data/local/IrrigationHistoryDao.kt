package com.example.hydroagrosense.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hydroagrosense.data.local.entities.IrrigationHistoryEntity

@Dao
interface IrrigationHistoryDao {

    @Query(
        """
        SELECT * FROM irrigation_history 
        WHERE deviceId = :deviceId 
        ORDER BY date DESC 
        LIMIT :limit
        """
    )
    fun observeHistory(deviceId: String, limit: Int): LiveData<List<IrrigationHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: List<IrrigationHistoryEntity>)

    @Query("DELETE FROM irrigation_history WHERE deviceId = :deviceId")
    suspend fun deleteAll(deviceId: String)
    
}
