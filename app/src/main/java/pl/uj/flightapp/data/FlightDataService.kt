package pl.uj.flightapp.data

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.domain.resources.FlightOfferSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pl.uj.flightapp.MainActivity
import pl.uj.flightapp.enums.City
import java.time.LocalDate


class FlightDataService {

    init {
        amadeus = Amadeus.Builder(MainActivity.appContext)
            .setClientId("3Alkj28AWnHqRRsbXwPPhvIdkX8v9lRR")
            .setClientSecret("23pQcgM9uje2lm6a")
            .build()
    }


    suspend fun fetchFlights(originCity: City, destinationCity: City, departureDate: LocalDate, adults: UInt = 1u, max: UInt = 10u): ApiResult<List<FlightOfferSearch>> {
        return withContext(Dispatchers.IO) {
            amadeus.shopping.flightOffersSearch.get(
                originLocationCode = originCity.iataCode,
                destinationLocationCode = destinationCity.iataCode,
                departureDate = departureDate.toString(),
                adults = adults.toInt(),
                max = max.toInt()
            )
        }
    }

    suspend fun fetchSampleFlight(): ApiResult<List<FlightOfferSearch>> {

        return withContext(Dispatchers.IO) {
            amadeus.shopping.flightOffersSearch.get(
                originLocationCode = City.WARSAW.iataCode,
                destinationLocationCode = City.LONDON.iataCode,
                departureDate = "2023-06-10",
                adults = 1,
                max = 2
            )
        }
    }

    fun mockupFlight(): FlightOfferSearch? {
        val res = runBlocking {
            amadeus.shopping.flightOffersSearch.get(
                originLocationCode = City.WARSAW.iataCode,
                destinationLocationCode = City.LONDON.iataCode,
                departureDate = "2023-06-01",
                adults = 1,
                max = 2
            )
        }

        if (res is ApiResult.Success) {
            return res.data.first()
        }

        return null
    }


    companion object {
        private lateinit var amadeus: Amadeus
    }

//    override val values: Sequence<FlightOfferSearch>
//        get() = runBlocking {
//            val res = amadeus.shopping.flightOffersSearch.get(
//                originLocationCode = City.WARSAW.iataCode,
//                destinationLocationCode = City.LONDON.iataCode,
//                departureDate = "2023-06-01",
//                adults = 1,
//                max = 2
//            )
//
//            if (res is ApiResult.Success) {
//                res.data.asSequence()
//            } else {
//                emptySequence()
//            }
//        }
}