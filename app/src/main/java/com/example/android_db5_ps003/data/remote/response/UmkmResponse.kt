package com.example.android_db5_ps003.data.remote.response

data class UmkmResponse(
    val data: List<UmkmItem>,
    val message: String,
    val status: Int
)

data class UmkmDetailResponse(
    val data: UmkmItem,
    val message: String,
    val status: Int
)

data class UmkmItem(
    val id: Int,
    val name: String,
    val owner: String,
    val img: String,
    val price: Int,
    val description: String,
    val link: String,
    val address: String,
    val category: String
)

