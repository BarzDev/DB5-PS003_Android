package com.example.android_db5_ps003

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun SmartCityApp(modifier: Modifier = Modifier) {
    Text(
        text = "Hello DB5-PS003",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_DB5PS003Theme {
        SmartCityApp()
    }
}