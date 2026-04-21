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
import mx.edu.itson.datastorelogin.data.PokemonDatabase
import mx.edu.itson.datastorelogin.data.PokemonRepository
import mx.edu.itson.datastorelogin.navigation.AppNavigation
import mx.edu.itson.datastorelogin.ui.theme.DataStoreLoginTheme
import mx.edu.itson.datastorelogin.viewModel.AuthViewModel
import mx.edu.itson.datastorelogin.viewModel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel = AuthViewModel(DataStoreManager(this))
        val pokemonViewModel = PokemonViewModel(PokemonRepository(PokemonDatabase.getDatabase(this).pokemonDAO()))
        setContent {
            DataStoreLoginTheme {
                AppNavigation(authViewModel, pokemonViewModel)
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