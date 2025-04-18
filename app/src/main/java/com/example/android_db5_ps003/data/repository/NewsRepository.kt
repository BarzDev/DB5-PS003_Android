package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.local.room.NewsDao
import com.example.android_db5_ps003.data.model.toEntity
import com.example.android_db5_ps003.data.model.toNewsItem
import com.example.android_db5_ps003.data.remote.response.NewsItem
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(val dao: NewsDao) {
    suspend fun getAllDataFromApi() {
        val response = ApiConfig.getApiService().getDataBerita()
        val entities = response.data.orEmpty()
            .filterNotNull()
            .map { it.toEntity() }

        dao.insertAll(entities)
    }

    fun getNewsFromRoom(): Flow<List<NewsItem>> {
        return dao.getNews().map { list ->
            list.map { it.toNewsItem() }
        }
    }

    fun getNewsById(id : Long) : Flow<List<NewsItem>> {
        return dao.getNewsById(id.toInt()).map { item ->
            item.map { it.toNewsItem() }
        }
    }
}