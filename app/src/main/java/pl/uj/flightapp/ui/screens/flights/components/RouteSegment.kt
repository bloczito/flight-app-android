package pl.uj.flightapp.ui.screens.flights.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyView() {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "item.carrierCode + item.number",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Divider()

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "TIME",
                )
                Text(
                    text = "CITY",
                )
            }

            Divider()

            Text(
                text = "TIME",
            )

            Divider()

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "TIME",
                )
                Text(
                    text = "CITY",
                )
            }
        }
        RouteOverlay {

        }
    }
}