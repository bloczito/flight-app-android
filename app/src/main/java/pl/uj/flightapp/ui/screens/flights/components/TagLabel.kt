package pl.uj.flightapp.ui.screens.flights.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TagLabel(text: String, icon: ImageVector? = null) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
    ){

        if (icon != null) {
            Icon(
                imageVector = icon,
                "tag_icon",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = text,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.Bold,
            )
    }
}


@Preview(showBackground = true)
@Composable
fun TagLabelPreview() {
    TagLabel(
        text = "piątek, 2 czerwca",
        icon = Icons.Default.Home
    )
}