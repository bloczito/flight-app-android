package pl.uj.flightapp.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.uj.flightapp.enums.GeneralRoutes
import pl.uj.flightapp.navigation.screens.SavedScreens
import pl.uj.flightapp.ui.screens.saved.SavedFlightsScreen


fun NavGraphBuilder.savedNavGraph(navController: NavController) {
    navigation(
        startDestination = SavedScreens.SavedList.route,
        route = GeneralRoutes.SAVED.route
    ) {
        composable(SavedScreens.SavedList.route) { SavedFlightsScreen() }
    }
}