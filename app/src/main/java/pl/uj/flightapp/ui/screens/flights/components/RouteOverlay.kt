package pl.uj.flightapp.ui.screens.flights.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RouteOverlay(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val lineThickness = 1.5.dp
    val circleDiameter = 3.dp

    BoxWithConstraints (modifier = Modifier.padding(start = 20.dp).then(modifier)) {
        content()

        Box(
            Modifier
                .matchParentSize()
                .drawBehind {
                    val heightOffset = circleDiameter.toPx()
                    val lineLength = size.height - 6 * circleDiameter.toPx() - 2
                    val xOffset = -30F
                    val yOffset = 27F

                    drawCircle(
                        Color.Red,
                        radius = circleDiameter.toPx(),
                        center = Offset(xOffset, yOffset)
                    )
                    drawRect(
                        Color.Red,
                        topLeft = Offset(
                            xOffset - 2,
                            heightOffset + 2* circleDiameter.toPx()
                        ),
                        size = Size(lineThickness.toPx(), lineLength),
                    )
                    drawCircle(
                        Color.Red,
                        radius = circleDiameter.toPx(),
                        center = Offset(
                            xOffset,
                            yOffset + lineLength
                        )
                    )
                }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RouteOverlayPreview() {
  RouteOverlay {
      Column {
          Text("Some text")
          Text("Some text")
          Text("Some text")
          Text("Some text")
          Text("Some text")
      }
  }
}

