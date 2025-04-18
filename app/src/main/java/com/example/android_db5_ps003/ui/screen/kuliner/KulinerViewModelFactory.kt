package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_db5_ps003.data.remote.retrofit.ApiService

class KulinerViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KulinerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KulinerViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}