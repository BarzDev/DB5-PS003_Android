package com.example.android_db5_ps003.data.remote.retrofit

import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.response.NewsResponse
import com.example.android_db5_ps003.data.remote.response.TourismDetailResponse
import com.example.android_db5_ps003.data.remote.response.TourismResponse
import com.example.android_db5_ps003.data.remote.response.UmkmDetailResponse
import com.example.android_db5_ps003.data.remote.response.UmkmResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/berita")
    suspend fun getDataBerita(): NewsResponse

    @GET("api/kuliner")
    suspend fun getDataKuliner(): KulinerResponse

    @GET("api/umkm")
    suspend fun getDataUmkm(): UmkmResponse

    @GET("api/umkm/{id}")
    suspend fun getDetailUmkm(@Path("id") id: Int): UmkmDetailResponse

    @GET("api/wisata")
    suspend fun getTourism(): TourismResponse

    @GET("api/wisata/{id}")
    suspend fun getTourismDetail(
        @Path("id") id: Int
    ): TourismDetailResponse

}