package pl.uj.flightapp.ui.screens.flights.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.uj.flightapp.R
import pl.uj.flightapp.enums.City
import pl.uj.flightapp.ui.theme.FlightAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun ChooseCityScreen(
    onSelect: (City) -> Unit,
    onBack: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        BasicTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.surface,
                                    MaterialTheme.shapes.small
                                )
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.surface,
                                    RoundedCornerShape(percent = 50)
                                )
                                .padding(vertical = 10.dp, horizontal = 20.dp)
                                .height(20.dp),
                            singleLine = true,
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                            textStyle = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize
                            ),
                            decorationBox = { innerTextField ->
                                Box {
                                   if (searchQuery.isEmpty()) {
                                       Text(
                                           text = stringResource(R.string.search_flights_select_input_placeholder),
                                           style = LocalTextStyle.current.copy(
                                               color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                               fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                           ),
                                       )
                                   }
                                    innerTextField()
                                }
                            }

                        )

                    },

                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_arrow")
                        }
                    }
                )
            }
    ) {
        LazyColumn(
            contentPadding = it
        ) {
            items(City.filterByQuery(searchQuery)) {  item ->
                Text(
                    text = item.cityName,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(item) }
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                )


                Divider()
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ChooseCityScreenPreview() {
    FlightAppTheme {
        ChooseCityScreen(
            onSelect = {},
            onBack = {}
        )
    }
}