package com.example.android_db5_ps003.data.remote.retrofit

import com.example.android_db5_ps003.data.remote.response.TourismResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/wisata")
    fun getTourism(): TourismResponse
}