package com.example.android_db5_ps003.data.remote.response

import com.google.gson.annotations.SerializedName

data class KulinerResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("ratings")
	val ratings: Any? = null,

	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
