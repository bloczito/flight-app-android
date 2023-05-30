package pl.uj.flightapp.ui.screens.flights.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amadeus.android.domain.resources.FlightOfferSearch
import pl.uj.flightapp.extensions.format
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun FlightSummary(flight: FlightOfferSearch, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${formatTime(getDepartureTimestamp(flight) ?: "")} - ${formatTime(getArrivalTimestamp(flight) ?: "")}")
                Text(if ((flight.itineraries?.first()?.segments?.size ?: 0) > 1) "Indirect" else "Direct")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${getDepartureAirport(flight)} - ${getArrivalAirport(flight)}")
                
                Text(flight.itineraries?.first()?.duration?.format() ?: "")
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${(flight.price?.grandTotal ?: 0)} ${flight.price?.currency ?: "EUR"}",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

private fun getDepartureTimestamp(flight: FlightOfferSearch): String? = flight.itineraries?.first()?.segments?.first()?.departure?.at

private fun getArrivalTimestamp(flight: FlightOfferSearch): String? = flight.itineraries?.first()?.segments?.last()?.arrival?.at

private fun getDepartureAirport(flight: FlightOfferSearch): String? = flight.itineraries?.first()?.segments?.first()?.departure?.iataCode

private fun getArrivalAirport(flight: FlightOfferSearch): String? = flight.itineraries?.first()?.segments?.last()?.arrival?.iataCode

private fun formatTime(timestamp: String): String = LocalDateTime
    .parse(timestamp)
    .format(DateTimeFormatter.ofPattern("HH:mm"))

fun Duration.format(extendedFormat: Boolean = false): String {
    return String
        .format(
            if (extendedFormat) "%d godz %d min" else "%02d:%02d",
            this.toHours(),
            this.toMinutes() % 60
        )
}


//@Preview(showBackground = true)
//@Composable
//fun FlightSummaryPreview(@PreviewParameter(FlightDataService::class) flight: FlightOfferSearch) {
//
//    FlightAppTheme {
//        FlightSummary(flight = flight)
//    }```Ō
//}