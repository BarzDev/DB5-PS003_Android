package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.remote.response.UmkmResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig

class UmkmRepository {

    suspend fun fetchData() : UmkmResponse {
        return ApiConfig.getApiService().getDataUmkm()
    }

    companion object {
        @Volatile
        private var instance: UmkmRepository? = null

        fun getInstance(): UmkmRepository = instance ?: synchronized(this) {
            UmkmRepository().apply { instance = this }
        }
    }
}