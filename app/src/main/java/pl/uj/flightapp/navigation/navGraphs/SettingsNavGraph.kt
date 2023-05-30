package pl.uj.flightapp.navigation.navGraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.uj.flightapp.enums.GeneralRoutes
import pl.uj.flightapp.navigation.screens.SettingsScreens
import pl.uj.flightapp.ui.screens.settings.SettingsScreen


fun NavGraphBuilder.settingsNavGraph(navController: NavController) {
    navigation(
        startDestination = SettingsScreens.Settings.route,
        route = GeneralRoutes.SETTINGS.route
    ) {
         composable(SettingsScreens.Settings.route) { SettingsScreen() }
    }
}