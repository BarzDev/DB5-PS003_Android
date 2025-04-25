package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import retrofit2.Response

class KulinerRepository(private val apiService: ApiService) {
    suspend fun fetchData(): KulinerResponse {
        return apiService.getDataKuliner()
    }

    companion object {
        @Volatile
        private var instance: KulinerRepository? = null

        fun getInstance(): KulinerRepository {
            return instance ?: synchronized(this) {
                instance ?: KulinerRepository(ApiConfig.getApiService()).also { instance = it }
            }
        }
    }
}