package com.example.android_db5_ps003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.android_db5_ps003.ui.screen.splash.SplashScreen
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_DB5PS003Theme {
                var isSplashVisible by rememberSaveable { mutableStateOf(true) }

                if (isSplashVisible) {
                    SplashScreen {
                        isSplashVisible = false
                    }
                } else {
                    SmartCityApp()
                }
            }
        }
    }
}