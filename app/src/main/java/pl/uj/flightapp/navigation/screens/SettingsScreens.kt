package pl.uj.flightapp.navigation.screens

private const val SETTINGS = "settings"

sealed class SettingsScreens (val route: String) {

    object Settings : SavedScreens(route = SETTINGS)

}
