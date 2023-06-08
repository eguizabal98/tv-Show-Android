package com.eem.home.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.eem.home.ui.HomeScreen

const val HomeRoute = "home"

fun NavGraphBuilder.homeScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit,
    onNavigateToAuthentication: () -> Unit
) {
    composable(
        route = HomeRoute
    ) {
        HomeScreen(
            showSnackBar = showSnackBar,
            navigateToAuthentication = onNavigateToAuthentication
        )
    }
}

fun NavController.navigateToHome(navOptionBuilder: (NavOptionsBuilder) -> Unit) {
    this.navigate(HomeRoute) {
        navOptionBuilder(this)
    }
}
