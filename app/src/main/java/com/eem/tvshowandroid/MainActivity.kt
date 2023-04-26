package com.eem.tvshowandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.tvshowandroid.ui.MainContainer

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TvShowAndroidTheme {
                MainContainer(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
