package com.example.android_db5_ps003.ui.screen.tourism_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.Tourism
import com.example.android_db5_ps003.data.repository.TourismRepository
import com.example.android_db5_ps003.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TourismDetailViewModel(private val repository: TourismRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Tourism>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Tourism>>
        get() = _uiState

    fun getTourismDetail(id: Int) {
        viewModelScope.launch {
            repository.getTourismDetail(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { tourismDetail ->
                    _uiState.value = tourismDetail
                }
        }
    }
}