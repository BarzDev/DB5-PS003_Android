package com.example.android_db5_ps003

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_db5_ps003.ui.components.TopBar
import com.example.android_db5_ps003.ui.navigation.NavigationItem
import com.example.android_db5_ps003.ui.navigation.Screen
import com.example.android_db5_ps003.ui.screen.catalogue.CatalogueScreen
import com.example.android_db5_ps003.ui.screen.emergency_call.EmergencyCallScreen
import com.example.android_db5_ps003.ui.screen.home.HomeScreen
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerDetailScreen
import com.example.android_db5_ps003.ui.screen.kuliner.KulinerScreen
import com.example.android_db5_ps003.ui.screen.news.NewsDetailScreen
import com.example.android_db5_ps003.ui.screen.news.NewsScreen
import com.example.android_db5_ps003.ui.screen.tourism.TourismScreen
import com.example.android_db5_ps003.ui.screen.tourism_detail.TourismDetailScreen
import com.example.android_db5_ps003.ui.screen.umkm.umkm_catalogue.UmkmCatalogueScreen
import com.example.android_db5_ps003.ui.screen.umkm.umkm_detail.UmkmDetailScreen
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun SmartCityApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Catalogue.route && currentRoute != Screen.Emergency.route) {
                TopBar(
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Catalogue.route || currentRoute == Screen.Emergency.route) {
                BottomBar(
                    navController = navController,
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToNewsDetail = { id ->
                        navController.navigate(Screen.NewsDetail.createRoute(id))
                    },
                    navigateToNewsCatalogue = {
                        navController.navigate(Screen.News.route)
                    }
                )
            }
            composable(Screen.Catalogue.route) {
                CatalogueScreen(
                    navController = navController,
                    navigateToKuliner = {
                        navController.navigate(Screen.Kuliner.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToTourism = {
                        navController.navigate(Screen.Tourism.route)
                    },
                    navigateToUmkm =   {
                        navController.navigate(Screen.UmkmCatalogue.route)
                    }
                )
            }
            composable(Screen.Tourism.route) {
                TourismScreen(
                    navigateToDetail = { tourismId ->
                        navController.navigate(Screen.TourismDetail.createRoute(tourismId))
                    }
                )
            }
            composable(
                route = Screen.TourismDetail.route,
                arguments = listOf(navArgument("tourismId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("tourismId") ?: -1
                val context = LocalContext.current
                TourismDetailScreen(
                    tourismId = id,
                    onNavigateButtonClicked = { url ->
                        navigate(context, url)
                    }
                )
            }
            composable(Screen.Emergency.route) {
                EmergencyCallScreen()
            }
            composable(Screen.News.route) {
                NewsScreen(
                    navigateToNewsDetail = { id ->
                        navController.navigate(Screen.NewsDetail.createRoute(id))
                    },
                )
            }
            composable(Screen.Kuliner.route) {
                KulinerScreen(
                    navController = navController
                )
            }
            composable(
                route = Screen.KulinerDetail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                KulinerDetailScreen(
                    kulinerId = id
                )
            }
            composable(
                route = Screen.NewsDetail.route,
                arguments = listOf(navArgument("newsId") { type = NavType.IntType })
            ) {
                val id: Int = it.arguments?.getInt("newsId") ?: -1
                NewsDetailScreen(
                    id = id
                )
            }

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

private fun navigate(context: Context, url: String) {
    val webpage: Uri = url.toUri()
    val intent = Intent(Intent.ACTION_VIEW, webpage)

    context.startActivity(intent)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SmartCityAppPreview() {
    Android_DB5PS003Theme {
        SmartCityApp()
    }
}