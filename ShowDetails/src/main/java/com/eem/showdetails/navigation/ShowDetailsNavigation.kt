package com.eem.showdetails.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eem.androidcommon.util.replaceNavArgument
import com.eem.showdetails.ui.showDetailsScreen

const val SHOW_ID_ARG = "showID"
const val ShowDetailsRoute = "showDetails/{$SHOW_ID_ARG}"

fun NavGraphBuilder.showDetailsScreen(
    showSnackBar: (String, SnackbarDuration) -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable(
        route = ShowDetailsRoute,
        arguments = listOf(navArgument(SHOW_ID_ARG) { type = NavType.StringType })
    ) {
        showDetailsScreen(
            showSnackBar = showSnackBar,
            navigateToHome = onNavigateToHome
        )
    }
}

fun NavController.navigateToShowDetails(showID: String) {
    this.navigate(ShowDetailsRoute.replaceNavArgument(SHOW_ID_ARG, showID))
}
