package com.example.android_db5_ps003.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_db5_ps003.data.repository.EmergencyRepository
import com.example.android_db5_ps003.ui.screen.emergency_call.EmergencyCallViewModel

class EmergencyViewModelFactory(private val repository: EmergencyRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmergencyCallViewModel::class.java)) {
            return EmergencyCallViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : "+modelClass.name)
    }
}