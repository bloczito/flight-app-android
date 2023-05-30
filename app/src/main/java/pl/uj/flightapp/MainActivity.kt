package pl.uj.flightapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.uj.flightapp.ui.screens.home.MainScreen
import pl.uj.flightapp.ui.theme.FlightAppTheme

class MainActivity : ComponentActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContext = applicationContext

        setContent {
            FlightAppTheme {
                MainScreen()
            }
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlightAppTheme {
        Greeting("Android")
    }
}