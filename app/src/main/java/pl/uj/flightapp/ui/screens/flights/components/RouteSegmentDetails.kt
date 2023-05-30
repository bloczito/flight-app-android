package pl.uj.flightapp.ui.screens.flights.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amadeus.android.domain.resources.FlightOfferSearch
import pl.uj.flightapp.enums.City
import pl.uj.flightapp.extensions.format
import pl.uj.flightapp.ui.screens.flights.vm.FlightsViewModel
import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun RouteSegmentDetails(
    segment: FlightOfferSearch.SearchSegment,
    vm: FlightsViewModel
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = segment.carrierCode?.let { vm.getAirLane(it) } ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )

            Text(
                text = segment.carrierCode + segment.number,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )

            Divider(color = MaterialTheme.colorScheme.onSurface)

            RouteOverlay(modifier = Modifier.padding(vertical = 5.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("${formatDepartureTime(segment)}   ${getDepartureCity(segment)}")
                    Divider(color = MaterialTheme.colorScheme.onSurface)
                    Text(segment.duration?.format() ?: "N/A")
                    Divider(color = MaterialTheme.colorScheme.onSurface)
                    Text("${formatArrivalTime(segment)}   ${getArrivalCity(segment)}")
                }
            }
        }
    }
}



private fun formatDepartureTime(segment: FlightOfferSearch.SearchSegment): String {
    return try {
        segment
            .departure
            ?.at
            ?.let { formatTime(it) }
            ?: "N/A"
    } catch (e: DateTimeException) {
        "N/A"
    }
}

private fun formatArrivalTime(segment: FlightOfferSearch.SearchSegment): String {
    return try {
        segment
            .arrival
            ?.at
            ?.let { formatTime(it) }
            ?: "N/A"
    } catch (e: DateTimeException) {
        "N/A"
    }
}

@Throws(DateTimeException::class)
private fun formatTime(at: String): String {
    return LocalDateTime
        .parse(at)
        .format(DateTimeFormatter.ofPattern("HH:mm"))
}

private fun getDepartureCity(segment: FlightOfferSearch.SearchSegment): String {
    val code = segment.departure!!.iataCode!!

    return City.getByCode(code)?.cityName ?: code
}

private fun getArrivalCity(segment: FlightOfferSearch.SearchSegment): String {
    val code = segment.arrival!!.iataCode!!

    return City.getByCode(code)?.cityName ?: code
}



//@Preview(
//    showBackground = true,
//    name = "Light Mode"
//)
//@Preview(
//    showBackground = true,
//    name = "Dark mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES
//)
//@Composable
//fun RouteSegmentDetailsPreview() {
//    FlightAppTheme {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            RouteSegmentDetails()
//        }
//    }
//}


