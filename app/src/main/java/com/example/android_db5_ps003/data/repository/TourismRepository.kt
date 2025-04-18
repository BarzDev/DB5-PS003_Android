package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.remote.response.Tourism
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import com.example.android_db5_ps003.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TourismRepository(
    private val apiService: ApiService
) {
    fun getAllTourism() : Flow<UiState<List<Tourism>>> {
        return flow {
            emit(UiState.Loading)
            try {
                val response = apiService.getTourism()
                val events = response.data
                emit(UiState.Success(events))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }

    fun getTourismDetail(id: Int) : Flow<UiState<Tourism>> {
        return flow {
            emit(UiState.Loading)
            try {
                val response = apiService.getTourismDetail(id)
                val event = response.data
                emit(UiState.Success(event))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }

    companion object {
        @Volatile
        private var instance: TourismRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(apiService)
            }.also { instance = it }
    }
}