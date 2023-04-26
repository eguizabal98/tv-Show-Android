package com.eem.authentication.ui

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eem.androidcommon.ui.components.LoadingIndicator
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.androidcommon.ui.theme.messageTextStyle
import com.eem.androidcommon.ui.theme.titleLargeTextStyle
import com.eem.androidcommon.ui.theme.titleSmallTextStyle
import com.eem.authentication.BuildConfig
import com.eem.authentication.R
import com.eem.authentication.ui.AuthenticationViewModel.BaseEvent.OnOpenHome
import com.eem.authentication.ui.AuthenticationViewModel.BaseEvent.OnShowCustomTab
import com.eem.authentication.ui.AuthenticationViewModel.BaseEvent.OnShowSnackBar
import com.eem.authentication.ui.AuthenticationViewModel.UIEvent.OnInitAuthentication
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AuthenticationScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit = { _: String, _: SnackbarDuration -> },
    viewModel: AuthenticationViewModel = koinViewModel(),
    allow: String? = null
) {
    val context = LocalContext.current
    viewModel.apply {
        LaunchedEffect(true) {
            viewModel.baseEvent.collect { event ->
                when (event) {
                    is OnShowCustomTab -> {
                        openTab(context, event.token)
                    }
                    is OnShowSnackBar -> {
                        showSnackBar(event.message, SnackbarDuration.Short)
                    }
                    is OnOpenHome -> {
                        showSnackBar("GO HOME", SnackbarDuration.Short)
                    }
                }
            }
        }
    }

    LaunchedEffect(true) {
        if (allow?.isNotEmpty() == true) {
            showSnackBar("Success Login", SnackbarDuration.Short)
            viewModel.getSessionId(allow)
        }
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.auth_background),
        contentDescription = stringResource(id = R.string.auth_background_content_description)
    )
    Column(
        modifier = Modifier.background(
            Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black)
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.auth_title),
                style = titleSmallTextStyle
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.auth_message))
                    addStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        ),
                        start = 10,
                        end = 48
                    )
                },
                style = titleLargeTextStyle
            )
            Text(
                modifier = Modifier.padding(top = 25.dp),
                text = stringResource(id = R.string.auth_description),
                style = messageTextStyle
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                onClick = {
                    viewModel.onUIEvent(OnInitAuthentication)
                }
            ) {
                Text(text = stringResource(id = R.string.auth_button))
            }
        }
    }
    LoadingIndicator(viewModel.uiState.isLoading)
}

fun openTab(context: Context, token: String) {
    val url = BuildConfig.AUTH_URL.replace(REQUEST_TOKEN, token)
    val builder = Builder()
    val customTabsIntent: CustomTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}

private const val REQUEST_TOKEN = "REQUEST_TOKEN"

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvShowAndroidTheme {
        AuthenticationScreen()
    }
}
