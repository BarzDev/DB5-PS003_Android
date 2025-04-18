package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import retrofit2.Response

class KulinerRepository {
    suspend fun fetchData(): Response<KulinerResponse> {
        return ApiConfig.getApiService().getDataKuliner()
    }

    companion object {
        @Volatile
        private var instance: KulinerRepository? = null
        fun getInstance(): KulinerRepository = instance ?: synchronized(this) {
            KulinerRepository().apply { instance = this }
        }
    }
}