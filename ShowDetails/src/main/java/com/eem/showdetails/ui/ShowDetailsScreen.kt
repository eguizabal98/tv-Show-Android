package com.eem.showdetails.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.eem.androidcommon.ui.components.LoadingIndicator
import com.eem.androidcommon.ui.theme.TvShowAndroidTheme
import com.eem.showdetails.ui.ShowDetailsViewModel.BaseEvent.OnShowSnackBar

@Composable
internal fun showDetailsScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit = { _: String, _: SnackbarDuration -> },
    viewModel: ShowDetailsViewModel = hiltViewModel(),
    navigateToHome: () -> Unit = {}
) {
    val context = LocalContext.current
    viewModel.apply {
        LaunchedEffect(true) {
            viewModel.baseEvent.collect { event ->
                when (event) {
                    is OnShowSnackBar -> {
                        showSnackBar(event.message, SnackbarDuration.Short)
                    }
                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.checkShowID()
    }

    LoadingIndicator(viewModel.uiState.isLoading)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvShowAndroidTheme {
        showDetailsScreen()
    }
}
