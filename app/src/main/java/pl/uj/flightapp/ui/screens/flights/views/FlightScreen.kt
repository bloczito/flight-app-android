package pl.uj.flightapp.ui.screens.flights.views

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pl.uj.flightapp.R
import pl.uj.flightapp.enums.City
import pl.uj.flightapp.enums.CityType
import pl.uj.flightapp.navigation.screens.FlightsScreens
import pl.uj.flightapp.ui.screens.flights.components.CityFakeInput
import pl.uj.flightapp.ui.screens.flights.vm.FlightsViewModel
import pl.uj.flightapp.ui.theme.AccentColor
import pl.uj.flightapp.ui.theme.FlightAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightsScreen(
    navController: NavController,
    flightsVM: FlightsViewModel
) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.search_flights_headline),
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }) {contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 45.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)

        ) {

            CityFakeInput(
                text = flightsVM.originCity?.cityName,
                placeholder = stringResource(R.string.search_flights_from_label),
                onClick = {
                    navController.navigate(FlightsScreens.SelectCity.passCityType(CityType.ORIGIN)) {
                        launchSingleTop = true
                    }
                }
            )


            CityFakeInput(
                text = flightsVM.destinationCity?.cityName,
                placeholder = stringResource(R.string.search_flights_to_label),
                onClick = {
                    navController.navigate(FlightsScreens.SelectCity.passCityType(CityType.DESTINATION)) {
                        launchSingleTop = true
                    }
                }
            )


            Button(
                onClick = {
                    flightsVM.fetchFlights()
                    navController.navigate(FlightsScreens.FlightsList.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
//                enabled = flightsVM.originCity != null && flightsVM.destinationCity != null,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(stringResource(id = R.string.search_flights_button))
            }
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FlightsScreenPreview() {
    val flightsVM = FlightsViewModel().apply {
        updateOriginCity(City.WARSAW)
    }

    FlightAppTheme {
        FlightsScreen(
            navController = rememberNavController(),
            flightsVM = flightsVM
        )
    }
}





