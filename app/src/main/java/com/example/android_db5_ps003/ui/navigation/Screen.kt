package com.example.android_db5_ps003.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Catalogue : Screen("catalogue")
    data object Tourism : Screen("catalogue/tourism")
    data object TourismDetail : Screen("catalogue/tourism/{tourismId}") {
        fun createRoute(tourismId: Int) = "catalogue/tourism/$tourismId"
    }

    data object Emergency : Screen("emergency")
    data object News : Screen("news")
    data object NewsDetail : Screen("news/{newsId}") {
        fun createRoute(newsId: Long) = "news/$newsId"
    }

    data object UmkmCatalogue : Screen("umkm")
    data object UmkmDetail : Screen("umkm_detail/{id}") {
        fun createRoute(id: Int) = "umkm_detail/$id"
    }

    data object Kuliner : Screen("kuliner")
    data object KulinerDetail : Screen("kuliner_detail/{id}") {
        fun createRoute(id: Int) = "kuliner_detail/$id"
    }
}