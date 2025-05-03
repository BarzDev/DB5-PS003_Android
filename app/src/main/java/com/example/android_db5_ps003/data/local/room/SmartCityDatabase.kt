package com.example.android_db5_ps003.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_db5_ps003.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class SmartCityDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var instance: SmartCityDatabase? = null
        fun getInstance(context: Context): SmartCityDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = SmartCityDatabase::class.java,
                    name = "smartcity.db"
                ).build()
            }
    }
}