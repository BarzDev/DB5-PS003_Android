package com.example.android_db5_ps003.ui.screen.umkm_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.UmkmItem
import com.example.android_db5_ps003.data.repository.UmkmRepository
import com.example.android_db5_ps003.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UmkmDetailViewModel(private val repository: UmkmRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<UmkmItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UmkmItem>> get() = _uiState

    fun getDetailUmkm(id: Int) {

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = repository.getDetail(id)
                _uiState.value = UiState.Success(response.data)
            } catch (e: Exception) {
                val msg = "Failed to get detail data"
                _uiState.value = UiState.Error(msg)
            }
        }
    }
}