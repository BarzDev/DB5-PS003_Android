package com.example.android_db5_ps003.di

import android.content.Context
import com.example.android_db5_ps003.data.local.room.SmartCityDatabase
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import com.example.android_db5_ps003.data.repository.EmergencyRepository
import com.example.android_db5_ps003.data.repository.KulinerRepository
import com.example.android_db5_ps003.data.repository.NewsRepository
import com.example.android_db5_ps003.data.repository.UmkmRepository
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerViewModel
import com.example.android_db5_ps003.ui.viewmodelfactory.KulinerViewModelFactory

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

    fun provideKulinerRepository(apiService: ApiService): KulinerRepository {
        return KulinerRepository(apiService)
    }

    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    fun provideKulinerViewModel(context: Context): KulinerViewModel {
        val repository = provideKulinerRepository(provideApiService())
        return KulinerViewModel(repository)
    }
}