package com.example.android_db5_ps003.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
//  sesuaikan dengan model response masing masing

    @GET("api/berita")
    fun getDataBerita(): Call<List<String>>

    @GET("api/kuliner")
    fun getDataKuliner(): Call<List<String>>

    @GET("api/umkm")
    fun getDataUmkm(): Call<List<String>>

    @GET("api/wisata")
    fun getDataWisata(): Call<List<String>>
}