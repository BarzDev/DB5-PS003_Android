package com.example.android_db5_ps003.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @field:ColumnInfo("id")
    val id: Int,
    @field:ColumnInfo("publishedAt")
    val publishedAt: String? = null,
    @field:ColumnInfo("urlToImage")
    val urlToImage: String? = null,
    @field:ColumnInfo("description")
    val description: String? = null,
    @field:ColumnInfo("title")
    val title: String? = null,
    @field:ColumnInfo("url")
    val url: String? = null,
    @field:ColumnInfo("content")
    val content: String? = null
)