package com.eem.tvshowandroid.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.eem.authentication.navigation.AuthenticationRoute
import com.eem.authentication.navigation.authenticationScreen
import com.eem.home.navigation.homeScreen
import com.eem.home.navigation.navigateToHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContainer(modifier: Modifier = Modifier) {
    val scaffoldAppState: SnackBarState = rememberSnackBarDemoAppState()
    val navController = rememberNavController()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldAppState.snackBarHostState) },
        content = { paddingValues ->
            MyNavigation(
                navController = navController,
                showSnackBar = { message: String, duration: SnackbarDuration ->
                    scaffoldAppState.showSnackBar(message, duration)
                },
                modifier = modifier.consumeWindowInsets(paddingValues)
            )
        }
    )
}

@Composable
fun MyNavigation(
    navController: NavHostController,
    showSnackBar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AuthenticationRoute,
        modifier = modifier
    ) {
        authenticationScreen(showSnackBar = showSnackBar) {
            navController.navigateToHome {
                it.popUpTo(AuthenticationRoute) {
                    inclusive = true
                }
            }
        }
        homeScreen(showSnackBar = showSnackBar) {
        }
    }
}

class SnackBarState(
    val snackBarHostState: SnackbarHostState,
    val snackBarScope: CoroutineScope
) {
    fun showSnackBar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        snackBarScope.launch {
            snackBarHostState.showSnackbar(message, duration = duration)
        }
    }
}

@Composable
fun rememberSnackBarDemoAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackBarScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackBarHostState, snackBarScope) {
    SnackBarState(
        snackBarHostState = snackBarHostState,
        snackBarScope = snackBarScope
    )
}
