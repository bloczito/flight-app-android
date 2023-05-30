//package pl.uj.flightapp
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import pl.uj.flightapp.ui.screens.flights.views.FlightsScreen
//import pl.uj.flightapp.views.HomeScreen
//import pl.uj.flightapp.ui.screens.saved.ProfileScreen
//import pl.uj.flightapp.ui.screens.settings.SettingsScreen
//
//@Composable
//fun BottomNavGraph(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        startDestination = BottomBarScreen.Flights.route
//    ) {
//        composable(BottomBarScreen.Flights.route) { FlightsScreen(navController = navController) }
//        composable(BottomBarScreen.Occasions.route) { HomeScreen() }
//        composable(BottomBarScreen.Saved.route) { ProfileScreen() }
//        composable(BottomBarScreen.Settings.route) { SettingsScreen() }
//    }
//
//}