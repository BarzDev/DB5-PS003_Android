package com.example.android_db5_ps003.di

import android.content.Context
import com.example.android_db5_ps003.data.local.room.SmartCityDatabase
import com.example.android_db5_ps003.data.repository.EmergencyRepository
import com.example.android_db5_ps003.data.repository.NewsRepository
import com.example.android_db5_ps003.data.repository.UmkmRepository

object Injection {
    fun provideEmergencyRepository(context: Context): EmergencyRepository {
        return EmergencyRepository()
    }

    fun provideNewsRepository(context: Context): NewsRepository {
        val database = SmartCityDatabase.getInstance(context)
        return NewsRepository(database.newsDao())
    }

    fun provideUmkmRepository(): UmkmRepository {
        return UmkmRepository.getInstance()
    }
}