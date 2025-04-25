package com.example.android_db5_ps003.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import com.example.android_db5_ps003.data.repository.KulinerRepository
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerViewModel

class KulinerViewModelFactory(
    private val repository: KulinerRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KulinerViewModel::class.java)) {
            return KulinerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}