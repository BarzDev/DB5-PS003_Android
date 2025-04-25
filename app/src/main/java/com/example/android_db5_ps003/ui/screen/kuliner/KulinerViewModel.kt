package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.data.repository.KulinerRepository
import com.example.android_db5_ps003.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KulinerViewModel(private val repository: KulinerRepository) : ViewModel() {
    private val _kulinerList = MutableStateFlow<List<DataItem>>(emptyList())
    val kulinerList: StateFlow<List<DataItem>> = _kulinerList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)

    private val _statuses = MutableStateFlow<List<String>>(emptyList())
    val statuses: StateFlow<List<String>> = _statuses.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<DataItem>>(emptyList())
    val searchResults: StateFlow<List<DataItem>> = _searchResults.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<List<DataItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<DataItem>>> = _uiState.asStateFlow()

    init {
        fetchKulinerData()
    }

    private fun fetchKulinerData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.fetchData()
                if (response.status == 200) {
                    val nonNullData = response.data?.filterNotNull() ?: emptyList()
                    _kulinerList.value = nonNullData
                    _statuses.value = nonNullData.mapNotNull { it.status }
                    _uiState.value = UiState.Success(nonNullData)
                } else {
                    val errorMsg = response.message ?: "Unknown error"
                    _errorMessage.value = errorMsg
                    _uiState.value = UiState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        performSearch()
    }

    private fun performSearch() {
        viewModelScope.launch {
            if (_searchQuery.value.isEmpty()) {
                _searchResults.value = emptyList()
            } else {
                val results = _kulinerList.value.filter { kuliner ->
                    kuliner.name?.contains(_searchQuery.value, ignoreCase = true) == true ||
                            kuliner.category?.contains(_searchQuery.value, ignoreCase = true) == true ||
                            kuliner.description?.contains(_searchQuery.value, ignoreCase = true) == true
                }
                _searchResults.value = results
            }
        }
    }
}