package pl.uj.flightapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import pl.uj.flightapp.enums.GeneralRoutes




sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {

    object Flights: BottomBarScreen(
        GeneralRoutes.FLIGHTS.route,
        R.string.search_flights_tab_label,
        Icons.Default.Home
    )

    object Saved: BottomBarScreen(
        GeneralRoutes.SAVED.route,
        R.string.saved_flights_tab_label,
        Icons.Default.Settings
    )

    object Settings: BottomBarScreen(
        GeneralRoutes.SETTINGS.route,
        R.string.settings_flights_tab_label,
        Icons.Default.Settings
    )
}
