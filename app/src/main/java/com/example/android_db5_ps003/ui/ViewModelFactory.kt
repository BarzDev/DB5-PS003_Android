package com.example.android_db5_ps003.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_db5_ps003.data.repository.UmkmRepository
import com.example.android_db5_ps003.ui.screen.umkm_catalogue.UmkmCatalogueViewModel
import com.example.android_db5_ps003.ui.screen.umkm_detail.UmkmDetailViewModel

class ViewModelFactory(private val repository: UmkmRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UmkmCatalogueViewModel::class.java)) {
            return UmkmCatalogueViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(UmkmDetailViewModel::class.java)) {
            return UmkmDetailViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}