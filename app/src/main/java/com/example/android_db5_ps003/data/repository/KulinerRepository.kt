package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiService

class KulinerRepository(private val apiService: ApiService) {
    suspend fun fetchData(): KulinerResponse {
        return apiService.getDataKuliner()
    }

    companion object {
        @Volatile
        private var instance: KulinerRepository? = null

    }
}