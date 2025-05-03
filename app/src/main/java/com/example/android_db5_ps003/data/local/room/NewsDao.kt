package com.example.android_db5_ps003.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_db5_ps003.data.local.entity.NewsEntity
import com.example.android_db5_ps003.data.remote.response.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getNews() : Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(news : List<NewsEntity>)

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNewsById(id: Int) : Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%'")
    fun getSearchedNews(query : String) : Flow<List<NewsEntity>>
}