package com.example.hydroagrosense.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hydroagrosense.data.local.entities.ConfigEntity

@Dao
interface ConfigDao {

    @Query("SELECT * FROM config WHERE deviceId = :deviceId LIMIT 1")
    fun observeConfig(deviceId: String): LiveData<ConfigEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(config: ConfigEntity)
}
