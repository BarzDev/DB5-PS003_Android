package com.example.android_db5_ps003.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_db5_ps003.data.repository.TourismRepository
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.screen.tourism.TourismViewModel
import com.example.android_db5_ps003.ui.screen.tourism_detail.TourismDetailViewModel

class TourismViewModelFactory private constructor(private val tourismRepository: TourismRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TourismViewModel::class.java)) {
            return TourismViewModel(tourismRepository) as T
        } else if (modelClass.isAssignableFrom(TourismDetailViewModel::class.java)) {
            return TourismDetailViewModel(tourismRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: TourismViewModelFactory? = null
        fun getInstance(): TourismViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: TourismViewModelFactory(Injection.provideTourismRepository())
            }.also { instance = it }
    }
}