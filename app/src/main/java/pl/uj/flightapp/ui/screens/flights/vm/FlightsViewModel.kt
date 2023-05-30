package pl.uj.flightapp.ui.screens.flights.vm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadeus.android.ApiResult
import com.amadeus.android.ApiResult.Success.Meta
import com.amadeus.android.domain.resources.FlightOfferSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.uj.flightapp.data.FlightDataService
import pl.uj.flightapp.enums.City
import java.time.LocalDate

class FlightsViewModel : ViewModel() {
    var originCity: City? by mutableStateOf(null)
        private set

    var destinationCity: City? by mutableStateOf(null)
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    private val _flights: MutableStateFlow<List<FlightOfferSearch>> = MutableStateFlow(emptyList())
    val flights: StateFlow<List<FlightOfferSearch>> = _flights

    private var metadata: Map<String, Map<String, String>> by mutableStateOf(emptyMap())

    private val flightDataService = FlightDataService()



    fun fetchFlights() {
        if (originCity == null || destinationCity == null) return

        viewModelScope.launch {
            isLoading = true
            Log.i("SEARCH_FLIGHTS", "Begin search")
            when (val response = flightDataService.fetchFlights(originCity!!, destinationCity!!, LocalDate.parse("2023-06-02"))) {
                is ApiResult.Success -> {
                    _flights.value = response.data

                    response.dictionaries?.let {
                        metadata = it as Map<String, Map<String, String>>
                    }
                }
                is ApiResult.Error -> {
                    Log.d("SEARCH_FLIGHTS", response.errors.toString())
                }
            }

            isLoading = false
            Log.i("SEARCH_FLIGHTS", "End search")
        }
    }

    fun updateOriginCity(selectedCity: City) {
        originCity = selectedCity

        if (destinationCity == selectedCity) { destinationCity = null }
    }

    fun updateDestinationCity(selectedCity: City) {
        destinationCity = selectedCity

        if (originCity == selectedCity) { originCity = null }
    }

    fun getAirLane(code: String): String = metadata["carriers"]?.get(code) ?: code

}