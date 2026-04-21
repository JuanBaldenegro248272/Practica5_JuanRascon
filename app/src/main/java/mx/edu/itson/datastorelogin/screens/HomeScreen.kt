package mx.edu.itson.datastorelogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.itson.datastorelogin.navigation.Screen

@Composable
fun HomeScreen(
    userName: String,
    navController: NavController,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hola! $userName"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.Bolsa.route)
            }
        ) {
            Text(
                text = "Ver mis pokemones"
            )
        }
        Button(onClick = onLogoutClick){
            Text(text = "Cerrar Sesion")
        }
    }
}