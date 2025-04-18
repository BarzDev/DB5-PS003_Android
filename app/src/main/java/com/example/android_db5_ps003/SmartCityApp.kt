package com.example.android_db5_ps003

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerScreen
import com.example.android_db5_ps003.ui.navigation.Screen
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerDetailScreen
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerViewModel
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerViewModelFactory
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun SmartCityApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val apiService = remember { ApiConfig.getApiService() }
    val viewModel: KulinerViewModel = viewModel(
        factory = KulinerViewModelFactory(apiService)
    )

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Kuliner.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Kuliner.route) {
                KulinerScreen(
                    viewModel = viewModel,
                    onItemClick = { id -> navController.navigate("kuliner/$id") }
                )
            }
            composable("kuliner/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                KulinerDetailScreen(
                    kulinerId = id,
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }

}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SmartCityAppPreview() {
    Android_DB5PS003Theme {
        SmartCityApp()
    }
}