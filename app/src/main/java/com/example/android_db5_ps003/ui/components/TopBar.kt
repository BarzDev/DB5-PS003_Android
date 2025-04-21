package com.example.android_db5_ps003.ui.components

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.ui.navigation.Screen

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
        Screen.Home.route -> stringResource(R.string.smart_city_kebumen)
        Screen.News.route -> stringResource(R.string.berita_terkini)
        Screen.NewsDetail.route -> stringResource(R.string.berita)
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
                    navController.navigateUp()
                }
            }
            ) {
                if (currentRoute != Screen.Home.route)
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
            }
        },
    )
}