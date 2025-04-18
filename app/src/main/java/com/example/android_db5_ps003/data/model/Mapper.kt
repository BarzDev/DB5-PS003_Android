package com.example.android_db5_ps003.data.model

import com.example.android_db5_ps003.data.local.entity.NewsEntity
import com.example.android_db5_ps003.data.remote.response.NewsItem
import java.text.SimpleDateFormat
import java.util.Locale

fun NewsItem.toEntity(): NewsEntity {
    return NewsEntity(
        this.id,
        this.publishedAt,
        this.urlToImage,
        this.description,
        this.title,
        this.url,
        this.content,
    )
}

fun NewsItem.toBannerData(): BannerData {
    return BannerData(
        urlImg = this.urlToImage ?: "",
        headline = this.title ?: "",
        id = this.id
    )
}

fun NewsEntity.toNewsItem(): NewsItem {
    return NewsItem(
        publishedAt = this.publishedAt?.let { convertDate(it) },
        urlToImage = this.urlToImage,
        description = this.description,
        id = this.id,
        title = this.title,
        url = this.url,
        content = this.content
    )
}

fun convertDate(date: String): String {
    val cleanedInput = date.replace(":", "")
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HHmmssZ", Locale.getDefault())
    val date = inputFormat.parse(cleanedInput)
    val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id"))
    return date?.let { outputFormat.format(it) } + " WIB"
}