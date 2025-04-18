package com.example.android_db5_ps003

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerScreen
import com.example.android_db5_ps003.ui.navigation.Screen
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun SmartCityApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
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
                KulinerScreen()
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