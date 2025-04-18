package com.example.android_db5_ps003.di

import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.data.repository.TourismRepository

object Injection {
    fun provideTourismRepository(): TourismRepository {
        val apiService = ApiConfig.getApiService()
        return TourismRepository.getInstance(apiService)
    }
}