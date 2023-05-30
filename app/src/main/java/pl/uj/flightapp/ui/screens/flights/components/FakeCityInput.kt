package pl.uj.flightapp.ui.screens.flights.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CityFakeInput(
    text: String?,
    placeholder: String,
    onClick: () -> Unit
) {
    Text(
        text = text ?: placeholder,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 3.dp,
                color = if (text.isNullOrBlank()) Color.Gray else Color.Red,
                shape = RoundedCornerShape(35)
            )
            .padding(horizontal = 20.dp)
            .padding(vertical = 15.dp)
            .clickable {
                onClick()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun CityFakeInputPreview() {
    CityFakeInput(
        text = "Kraków",
        placeholder = "From",
        onClick = {}
    )
}