package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class KulinerViewModel(private val apiService: ApiService) : ViewModel() {
    private val _kulinerList = MutableStateFlow<List<DataItem>>(emptyList())
    val kulinerList: StateFlow<List<DataItem>> = _kulinerList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

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
                        // Extract unique categories
                        _categories.value = data.filterNotNull()
                            .map { it.category ?: "" }
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

    fun getKulinerByCategory(category: String): List<DataItem> {
        return _kulinerList.value.filter { it.category == category }
    }
}