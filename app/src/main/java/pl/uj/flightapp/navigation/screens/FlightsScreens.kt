package pl.uj.flightapp.navigation.screens

import pl.uj.flightapp.enums.CityType

private const val SEARCH_FLIGHTS = "search_flights"
private const val SELECT_CITY = "select_city"
private const val FLIGHTS_LIST = "flights_list"
private const val FLIGHT_DETAILS = "flights_details"

const val SELECT_CITY_TYPE_KEY = "city_type"


sealed class FlightsScreens (val route: String) {

    object SearchFlights: FlightsScreens(route = SEARCH_FLIGHTS)

    object SelectCity: FlightsScreens(route = "$SELECT_CITY/{$SELECT_CITY_TYPE_KEY}") {
        fun passCityType(type: CityType): String = this.route.replace("{$SELECT_CITY_TYPE_KEY}", type.toString())
    }

    object FlightsList: FlightsScreens(route = FLIGHTS_LIST)

    object FlightDetails : FlightsScreens(route = FLIGHT_DETAILS) {
        fun getFlightKey(): String = "FLIGHT"
    }
}