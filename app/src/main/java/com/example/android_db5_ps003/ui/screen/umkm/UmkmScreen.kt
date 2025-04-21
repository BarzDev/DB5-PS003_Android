package com.example.android_db5_ps003.ui.screen.umkm

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_db5_ps003.ui.components.TopBar
import com.example.android_db5_ps003.ui.navigation.Screen
import com.example.android_db5_ps003.ui.screen.umkm.umkm_catalogue.UmkmCatalogueScreen
import com.example.android_db5_ps003.ui.screen.umkm.umkm_detail.UmkmDetailScreen

@Composable
fun UmkmScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = { TopBar(navController) },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.UmkmCatalogue.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.UmkmCatalogue.route) {
                UmkmCatalogueScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.UmkmDetail.createRoute(id))
                    },
                )
            }
            composable(
                route = Screen.UmkmDetail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                UmkmDetailScreen(id = it.arguments?.getInt("id") ?: 0)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UmkmScreenPreview() {
    UmkmScreen()
}