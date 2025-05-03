package com.example.android_db5_ps003.data.remote.response

import com.google.gson.annotations.SerializedName

data class TourismResponse(

    @field:SerializedName("data")
    val data: List<Tourism>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class Tourism(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("img")
    val img: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("link")
    val link: String,
)
