package mx.edu.itson.datastorelogin

import mx.edu.itson.datastorelogin.data.DataStoreManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.itson.datastorelogin.navigation.AppNavigation
import mx.edu.itson.datastorelogin.ui.theme.DataStoreLoginTheme
import mx.edu.itson.datastorelogin.viewModel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel = AuthViewModel(DataStoreManager(this))
        setContent {
            DataStoreLoginTheme {
                AppNavigation(authViewModel)
            }
        }
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
    DataStoreLoginTheme {
        Greeting("Android")
    }
}