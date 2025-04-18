package com.example.android_db5_ps003.data.remote.retrofit

import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/kuliner")
    suspend fun getDataKuliner(): Response<KulinerResponse>
}