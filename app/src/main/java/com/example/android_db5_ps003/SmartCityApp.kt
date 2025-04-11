package com.example.android_db5_ps003

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_db5_ps003.ui.navigation.NavigationItem
import com.example.android_db5_ps003.ui.navigation.Screen
import com.example.android_db5_ps003.ui.screen.catalogue.CatalogueScreen
import com.example.android_db5_ps003.ui.screen.emergency_call.EmergencyCallScreen
import com.example.android_db5_ps003.ui.screen.home.HomeScreen
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
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Catalogue.route) {
                CatalogueScreen()
            }
            composable(Screen.Emergency.route) {
                EmergencyCallScreen()
            }
        }
    }

}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_catalogue),
                icon = ImageVector.vectorResource(id = R.drawable.ic_catalogue),
                screen = Screen.Catalogue,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_emergency),
                icon = ImageVector.vectorResource(id = R.drawable.ic_emergency_call),
                screen = Screen.Emergency
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmartCityAppPreview() {
    Android_DB5PS003Theme {
        SmartCityApp()
    }
}