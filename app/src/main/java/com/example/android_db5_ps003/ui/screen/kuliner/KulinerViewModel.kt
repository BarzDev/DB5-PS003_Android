package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KulinerViewModel(private val apiService: ApiService) : ViewModel() {
    private val _kulinerList = MutableStateFlow<List<DataItem>>(emptyList())
    val kulinerList: StateFlow<List<DataItem>> = _kulinerList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _statuses = MutableStateFlow<List<String>>(emptyList())
    val statuses: StateFlow<List<String>> = _statuses.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<DataItem>>(emptyList())
    val searchResults: StateFlow<List<DataItem>> = _searchResults.asStateFlow()

    init {
        fetchKulinerData()
    }

    private fun fetchKulinerData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getDataKuliner()
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        _kulinerList.value = data.filterNotNull()
                        _statuses.value = data.filterNotNull()
                            .map { it.status ?: "" }
                            .distinct()
                            .filter { it.isNotEmpty() }
                    }
                } else {
                    _errorMessage.value = "Failed to load data: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
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

    fun searchResults(): List<DataItem> {
        return _kulinerList.value.filter {
            it.name?.contains(_searchQuery.value, ignoreCase = true) == true ||
                    it.category?.contains(_searchQuery.value, ignoreCase = true) == true
        }
    }

    fun getKulinerByStatus(status: String): List<DataItem> {
        return _kulinerList.value.filter { it.status == status }
    }

    fun getRecommendedKuliner(): List<DataItem> {
        return _kulinerList.value.filter { it.status == "Recommended" }
    }
}