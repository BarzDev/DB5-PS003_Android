package com.example.android_db5_ps003.data.remote.retrofit

import com.example.android_db5_ps003.data.remote.response.TourismDetailResponse
import com.example.android_db5_ps003.data.remote.response.TourismResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/wisata")
    suspend fun getTourism(): TourismResponse

    @GET("api/wisata/{id}")
    suspend fun getTourismDetail(
        @Path("id") id: Int
    ): TourismDetailResponse
}