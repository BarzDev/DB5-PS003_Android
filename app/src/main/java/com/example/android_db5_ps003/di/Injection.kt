package com.example.android_db5_ps003.di

import com.example.android_db5_ps003.data.repository.UmkmRepository

object Injection {
    fun provideUmkmRepository(): UmkmRepository {
        return UmkmRepository.getInstance()
    }
}