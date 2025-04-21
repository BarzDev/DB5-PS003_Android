package com.example.android_db5_ps003.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Catalogue : Screen("catalogue")
    data object Emergency : Screen("emergency")
    data object News : Screen("news")
    data object NewsDetail : Screen("news/{newsId}") {
        fun createRoute(newsId : Long) = "news/$newsId"
    }
    data object UmkmCatalogue : Screen("umkm")
    data object UmkmDetail : Screen("umkm_detail/{id}") {
        fun createRoute(id: Int) = "umkm_detail/$id"
    }
}