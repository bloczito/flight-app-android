package pl.uj.flightapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import pl.uj.flightapp.enums.GeneralRoutes
import pl.uj.flightapp.navigation.navGraphs.savedNavGraph
import pl.uj.flightapp.navigation.navGraphs.searchFlightsGraph
import pl.uj.flightapp.navigation.navGraphs.settingsNavGraph


@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = GeneralRoutes.FLIGHTS.route,
        route = GeneralRoutes.ROOT.route
    ) {

        searchFlightsGraph(navController = navController)
        savedNavGraph(navController = navController)
        settingsNavGraph(navController = navController)
    }
}