package pl.uj.flightapp.navigation.screens

private const val SAVED_LIST = "saved_list"

sealed class SavedScreens (val route: String) {

    object SavedList : SavedScreens(route = SAVED_LIST)

}
