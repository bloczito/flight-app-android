package pl.uj.flightapp.ui.screens.flights.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pl.uj.flightapp.navigation.screens.FlightsScreens
import pl.uj.flightapp.ui.screens.flights.components.FlightSummary
import pl.uj.flightapp.ui.screens.flights.vm.FlightsViewModel
import pl.uj.flightapp.ui.theme.FlightAppTheme


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FlightsListView(
    flightsVM: FlightsViewModel,
    navController: NavController
) {
    val flights by flightsVM.flights.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(FlightsScreens.SearchFlights.route) {
                            popUpTo(FlightsScreens.SearchFlights.route)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_arrow")
                    }
                },
                title = {
                    Text(
                        text = "Loty do: " + flightsVM.destinationCity?.cityName,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) {contentPadding ->

        if (flightsVM.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(contentPadding)) {
                items(items = flights) {
                    FlightSummary(flight = it) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = FlightsScreens.FlightDetails.getFlightKey(),
                            value = it
                        )
                        navController.navigate(FlightsScreens.FlightDetails.route)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FlightsListViewPreview() {
    FlightAppTheme {
        FlightsListView(
            flightsVM = FlightsViewModel(),
            navController = rememberNavController()
        )
    }
}
