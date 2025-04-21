package com.example.android_db5_ps003.ui.screen.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navigateToHome()
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier
                .size(width = 275.dp, height = 212.dp)
        )
        Text(
            text = stringResource(R.string.smart_city_kebumen),
            fontSize = 20.sp,
            )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    Android_DB5PS003Theme {
        SplashScreen() {

        }
    }
}