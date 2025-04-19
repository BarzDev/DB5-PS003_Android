package com.example.android_db5_ps003.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_db5_ps003.data.repository.UmkmRepository
import com.example.android_db5_ps003.ui.screen.umkm.umkm_catalogue.UmkmCatalogueViewModel
import com.example.android_db5_ps003.ui.screen.umkm.umkm_detail.UmkmDetailViewModel

class UmkmViewModelFactory(private val repository: UmkmRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UmkmCatalogueViewModel::class.java) -> {
                UmkmCatalogueViewModel(repository) as T
            }

            modelClass.isAssignableFrom(UmkmDetailViewModel::class.java) -> {
                UmkmDetailViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}