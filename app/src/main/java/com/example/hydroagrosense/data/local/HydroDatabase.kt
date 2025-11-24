package com.example.hydroagrosense.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hydroagrosense.data.local.entities.ConfigEntity
import com.example.hydroagrosense.data.local.entities.IrrigationHistoryEntity
import com.example.hydroagrosense.data.local.entities.TelemetryEntity

@Database(
    entities = [
        ConfigEntity::class,
        TelemetryEntity::class,
        IrrigationHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HydroDatabase : RoomDatabase() {

    abstract fun configDao(): ConfigDao
    abstract fun telemetryDao(): TelemetryDao
    abstract fun irrigationHistoryDao(): IrrigationHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HydroDatabase? = null

        fun getInstance(context: Context): HydroDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HydroDatabase::class.java,
                    "hydro_agro_sense.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
