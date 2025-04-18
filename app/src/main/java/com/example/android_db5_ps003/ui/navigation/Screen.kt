package com.example.android_db5_ps003.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Catalogue : Screen("catalogue")
    data object Tourism : Screen("catalogue/tourism")
    data object TourismDetail : Screen("catalogue/tourism/{tourismId}") {
        fun createRoute(tourismId: Int) = "catalogue/tourism/$tourismId"
    }
    data object Emergency : Screen("emergency")
}