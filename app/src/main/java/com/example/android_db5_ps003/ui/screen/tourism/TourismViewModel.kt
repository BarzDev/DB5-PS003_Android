package com.example.android_db5_ps003.ui.screen.tourism

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.Tourism
import com.example.android_db5_ps003.data.repository.TourismRepository
import com.example.android_db5_ps003.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TourismViewModel(private val repository: TourismRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Tourism>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Tourism>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllTourism() {
        viewModelScope.launch {
            repository.getAllTourism()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { events ->
                    _uiState.value = events
                }
        }
    }

    fun search(query: String) {
        _query.value = query
        viewModelScope.launch {
            repository.searchTourism(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { tourismList ->
                    _uiState.value = UiState.Success(tourismList)
                }
        }
    }
}