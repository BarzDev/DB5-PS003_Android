package com.example.android_db5_ps003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_DB5PS003Theme {
                SmartCityApp()
            }
        }
    }
}