package com.eem.authentication.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.eem.authentication.ui.AuthenticationScreen

const val AuthenticationRoute = "authentication"
private const val URI = "tvapp://tvshowandroid/approved"
private const val ALLOW_ARG = "allow"
private const val ALLOW_PARAM_KEY = "allow={allow}"

fun NavGraphBuilder.authenticationScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable(
        route = AuthenticationRoute,
        arguments = listOf(navArgument(ALLOW_ARG) { type = NavType.StringType }),
        deepLinks = listOf(navDeepLink { uriPattern = "$URI?$ALLOW_PARAM_KEY" })
    ) { backStackEntry ->
        AuthenticationScreen(
            showSnackBar = showSnackBar,
            allow = backStackEntry.arguments?.getString(ALLOW_ARG),
            navigateToHome = onNavigateToHome
        )
    }
}

fun NavController.navigateToAuthentication() {
    this.navigate(AuthenticationRoute)
}
