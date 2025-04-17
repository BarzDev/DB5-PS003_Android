package com.example.android_db5_ps003.ui.screen.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.NewsItem
import com.example.android_db5_ps003.data.repository.NewsRepository
import com.example.android_db5_ps003.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<NewsItem>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<NewsItem>>> get() = _uiState

    init {
        viewModelScope.launch {
            newsRepository.getAllDataFromApi()
        }
    }

    fun getNewsData() {
        viewModelScope.launch {
            newsRepository.getNewsFromRoom()
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }
                .collect { data ->
                    _uiState.value = UiState.Success(data)
                }
        }
    }
}