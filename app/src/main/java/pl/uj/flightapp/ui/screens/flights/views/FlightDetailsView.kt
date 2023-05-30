package pl.uj.flightapp.ui.screens.flights.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amadeus.android.domain.resources.FlightOfferSearch
import pl.uj.flightapp.R
import pl.uj.flightapp.enums.City
import pl.uj.flightapp.extensions.format
import pl.uj.flightapp.navigation.screens.FlightsScreens
import pl.uj.flightapp.ui.screens.flights.components.RouteSegmentDetails
import pl.uj.flightapp.ui.screens.flights.components.TagLabel
import pl.uj.flightapp.ui.screens.flights.components.format
import pl.uj.flightapp.ui.screens.flights.vm.FlightsViewModel
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset


@ExperimentalMaterial3Api
@Composable
fun FlightDetailsView(flight: FlightOfferSearch, navController: NavController, vm: FlightsViewModel) {

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(FlightsScreens.FlightsList.route) {
                            popUpTo(FlightsScreens.FlightsList.route)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_arrow")
                    }
                },
                title = {
                    Text("")
                }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = getFinalDestination(flight),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "star_icon",
                    modifier = Modifier.size(30.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.search_flights_details_from_label) + " " + getOriginCity(flight),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )

                Text(
                    "${flight.price?.grandTotal ?: ""} ${flight.price?.currency ?: ""}",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                TagLabel(text = getDepartureDayLabel(flight))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(imageVector = Icons.Outlined.Schedule, contentDescription = "schedule_icon")
                    Text(text = flight.itineraries?.first()?.duration?.format() ?: "")
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                (flight.itineraries?.first()?.segments ?: emptyList()).forEachIndexed { idx, item ->
                    RouteSegmentDetails(item, vm)

                    if (idx < (flight.itineraries?.first()?.segments?.size ?: 0) - 1) {
                        Column {
                            // TODO: change to string resource
                            TagLabel(text = "Oczekiwanie", icon = Icons.Default.Schedule)
                            Text(text = getChangeDuration(flight, idx))
                        }
                        
                    }
                }
            }
        }
    }
}

private fun getFinalDestination(flight: FlightOfferSearch): String {
    val code = flight.itineraries?.first()?.segments?.last()?.arrival?.iataCode

    if (code.isNullOrBlank()) return ""

    return City.getByCode(code)?.cityName ?: code
}

private fun getOriginCity(flight: FlightOfferSearch): String {
    val code = flight.itineraries?.first()?.segments?.first()?.departure?.iataCode

    if (code.isNullOrBlank()) return ""

    return City.getByCode(code)?.cityName ?: code
}

private fun getDepartureDayLabel(flight: FlightOfferSearch): String {
    val departureAtStr = flight.itineraries?.first()?.segments?.first()?.departure?.at

    if (departureAtStr.isNullOrBlank()) return ""

    val departureAt = LocalDateTime.parse(departureAtStr)

    return departureAt.dayOfWeek.localDisplayName() + ", " + departureAt.dayOfMonth + " " + departureAt.month.localMonthName()
}

fun getChangeDuration(flight: FlightOfferSearch, segmentId: Int): String {
    return flight
        .itineraries
        ?.first()
        ?.segments
        ?.let {
            if (segmentId < it.size && segmentId + 1 < it.size) {
                val previous = LocalDateTime.parse(it[segmentId].arrival!!.at)
                val next = LocalDateTime.parse(it[segmentId + 1].departure!!.at)

                Log.d("Details", previous.toString())
                Log.d("Details", next.toString())

                return@let Duration.between(previous, next).format(true)
            } else {
                return@let "N/A"
            }
        }
        ?: "N/A"
}


// TODO: move to separate files
fun DayOfWeek.localDisplayName(): String {
    return when (this.value) {
        1 -> "Poniedziałek"
        2 -> "Wtorek"
        3 -> "Środa"
        4 -> "Czwartek"
        5 -> "Piątek"
        6 -> "Sobota"
        7 -> "Niedziela"
        else -> ""
    }
}

fun Month.localMonthName(): String {
    return when (this) {
        Month.JANUARY -> "Styczeń"
        Month.FEBRUARY -> "Luty"
        Month.MARCH -> "Marzec"
        Month.APRIL -> "Kwiecień"
        Month.MAY -> "Maj"
        Month.JUNE -> "Czerwiec"
        Month.JULY -> "Lipiec"
        Month.AUGUST -> "Sierpień"
        Month.SEPTEMBER -> "Wrzesień"
        Month.OCTOBER -> "Październik"
        Month.NOVEMBER -> "Listopad"
        Month.DECEMBER -> "Grudzień"
        else -> ""
    }
}