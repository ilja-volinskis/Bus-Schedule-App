package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(version = 1, entities = [BusSchedule::class], exportSchema = false)
abstract class BusScheduleDatabase: RoomDatabase() {
    abstract fun busScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var Instance: BusScheduleDatabase? = null
        fun getInstance(context: Context): BusScheduleDatabase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, BusScheduleDatabase::class.java, "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}