package com.example.android_db5_ps003.data.remote.response

import com.google.gson.annotations.SerializedName

data class TourismDetailResponse(

    @field:SerializedName("data")
    val data: Tourism,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int

)
