package com.example.android_db5_ps003.ui.navigation

sealed class Screen(val route: String) {
    data object Kuliner : Screen("kuliner")
}