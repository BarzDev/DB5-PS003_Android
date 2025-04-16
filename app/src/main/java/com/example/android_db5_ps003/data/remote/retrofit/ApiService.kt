package com.example.android_db5_ps003.data.remote.retrofit

import com.example.android_db5_ps003.data.remote.response.NewsResponse
import com.example.android_db5_ps003.data.remote.response.UmkmDetailResponse
import com.example.android_db5_ps003.data.remote.response.UmkmResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/berita")
    suspend fun getDataBerita(): NewsResponse

    @GET("api/kuliner")
    fun getDataKuliner(): Call<List<String>>

    @GET("api/umkm")
    suspend fun getDataUmkm(): UmkmResponse

    @GET("api/umkm/{id}")
    suspend fun getDetailUmkm(@Path("id") id: Int): UmkmDetailResponse

    @GET("api/wisata")
    fun getDataWisata(): Call<List<String>>

}