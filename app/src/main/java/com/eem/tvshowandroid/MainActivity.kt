package com.eem.tvshowandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.domain.interactor.authentication.GetGuestTokenUseCase
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val getGuestTokenUseCase: GetGuestTokenUseCase by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TvShowAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    var text by remember { mutableStateOf("Loading") }
                    LaunchedEffect(true) {
                        scope.launch {
                            getGuestTokenUseCase().collectLatest {
                                text = when (it) {
                                    is ResultWrapper.Error -> {
                                        it.message ?: "Error"
                                    }
                                    ResultWrapper.Loading -> {
                                        "Loading"
                                    }
                                    ResultWrapper.NetworkError -> {
                                        "Network Error"
                                    }
                                    is ResultWrapper.Success -> {
                                        it.data.guestSessionId
                                    }
                                }
                            }
                        }
                    }
                    Greeting(text)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvShowAndroidTheme {
        Greeting("Android")
    }
}
