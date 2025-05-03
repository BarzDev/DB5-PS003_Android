package com.example.android_db5_ps003.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(4000)
        navigateToHome()
    }
    val context = LocalContext.current
    val gifPainter = rememberAsyncImagePainter(
        remember {
            ImageRequest.Builder(context)
                .data(R.drawable.logo)
                .decoderFactory(GifDecoder.Factory())
                .build()
        }
    )
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = gifPainter,
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier.size(width = 275.dp, height = 212.dp)
        )
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.smart_part) + " ")
                withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.city_part))
                }
                append(" " + stringResource(R.string.kebumen_part))
            },
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
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