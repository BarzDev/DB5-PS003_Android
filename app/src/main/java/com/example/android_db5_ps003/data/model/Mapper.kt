package com.example.android_db5_ps003.data.model

import com.example.android_db5_ps003.data.local.entity.NewsEntity
import com.example.android_db5_ps003.data.remote.response.NewsItem
import com.example.android_db5_ps003.data.remote.response.NewsResponse

fun NewsItem.toEntity() : NewsEntity {
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

fun NewsEntity.toNewsItem() : NewsItem {
    return NewsItem(
        publishedAt = this.publishedAt,
        urlToImage = this.urlToImage,
        description = this.description,
        id = this.id,
        title = this.title,
        url = this.url,
        content = this.content
    )
}