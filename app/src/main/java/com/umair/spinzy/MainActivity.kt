package com.umair.spinzy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.umair.spinzy.ui.SpinzyApp
import com.umair.spinzy.ui.theme.SpinzyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // System splash hands over to the animated in-app splash (ui/screens/SplashScreen.kt)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpinzyTheme { SpinzyApp() }
        }
    }
}
