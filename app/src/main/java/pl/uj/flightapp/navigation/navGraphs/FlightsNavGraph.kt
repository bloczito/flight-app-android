package pl.uj.flightapp.navigation.navGraphs

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.amadeus.android.domain.resources.FlightOfferSearch
import pl.uj.flightapp.enums.CityType
import pl.uj.flightapp.enums.GeneralRoutes
import pl.uj.flightapp.navigation.screens.FlightsScreens
import pl.uj.flightapp.navigation.screens.SELECT_CITY_TYPE_KEY
import pl.uj.flightapp.ui.screens.flights.views.ChooseCityScreen
import pl.uj.flightapp.ui.screens.flights.views.FlightDetailsView
import pl.uj.flightapp.ui.screens.flights.views.FlightsListView
import pl.uj.flightapp.ui.screens.flights.views.FlightsScreen
import pl.uj.flightapp.ui.screens.flights.vm.FlightsViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.searchFlightsGraph(
    navController: NavController,
    flightsVM: FlightsViewModel = FlightsViewModel()
) {
    navigation(
        startDestination = FlightsScreens.SearchFlights.route,
        route = GeneralRoutes.FLIGHTS.route
    ) {

        composable(route = FlightsScreens.SearchFlights.route) {
            FlightsScreen(navController = navController, flightsVM = flightsVM)
        }

        composable(
            route = FlightsScreens.SelectCity.route,
            arguments = listOf(
                navArgument(SELECT_CITY_TYPE_KEY) {
//                    type = NavType.EnumType(CityType::class.java)
                    type = NavType.StringType
                }
            )
        ) {
            ChooseCityScreen(
                onSelect = { city ->
                    try {
                        val cityType = CityType.valueOf(it.arguments?.getString(SELECT_CITY_TYPE_KEY) ?: "")

                        if (cityType == CityType.ORIGIN) {
                            flightsVM.updateOriginCity(city)
                        } else {
                            flightsVM.updateDestinationCity(city)
                        }

                    } catch (e: Exception) {
                        Log.d("SearchFlightsGraph", "Exception: ${e.message}")
                    } finally {
                        navController.navigate(GeneralRoutes.FLIGHTS.route) {
                            popUpTo(GeneralRoutes.FLIGHTS.route) { inclusive = true }
                        }
                    }
                },
                onBack = {
                    navController.navigate(GeneralRoutes.FLIGHTS.route) {
                        popUpTo(GeneralRoutes.FLIGHTS.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = FlightsScreens.FlightsList.route) {
            FlightsListView(flightsVM = flightsVM, navController = navController)
        }

        composable(route = FlightsScreens.FlightDetails.route) {
            val flight = navController.previousBackStackEntry?.savedStateHandle?.get<FlightOfferSearch>(FlightsScreens.FlightDetails.getFlightKey())

            FlightDetailsView(flight = flight!!, navController = navController, flightsVM)
        }
    }
}