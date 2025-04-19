package com.example.android_db5_ps003.ui.screen.umkm

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_db5_ps003.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val title = when (currentRoute) {
        Screen.UmkmCatalogue.route -> stringResource(R.string.katalog_umkm)
        Screen.UmkmDetail.route -> stringResource(R.string.detail_umkm)
        else -> stringResource(R.string.app_name)
    }

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        ),
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = {
                if (currentRoute == Screen.UmkmCatalogue.route) {
                    (context as? Activity)?.finish()
                } else {
                    navController.popBackStack()
                }
            }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun UmkmScreenPreview() {
    UmkmScreen()
}