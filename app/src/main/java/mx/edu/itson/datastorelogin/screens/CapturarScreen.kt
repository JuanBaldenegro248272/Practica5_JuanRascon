package mx.edu.itson.datastorelogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.itson.datastorelogin.viewModel.PokemonViewModel

@Composable
fun CapturarScreen(
    navController: NavController,
    viewModel: PokemonViewModel
) {
    val pokemon = viewModel.pokemonSalvaje

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Zona de captura",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (pokemon == null) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD96C6C),
                    contentColor = Color.White
                ),
                onClick = { viewModel.buscarPokemonSalvaje() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar en la hierba")
            }
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "¡Apareció un pokémon salvaje!",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Nombre: ${pokemon.name}")
                    Text("Numero: ${pokemon.number}")
                    Text("Tipo: ${pokemon.type}")
                    Text("Nivel: ${pokemon.level}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD96C6C),
                    contentColor = Color.White
                ),
                onClick = { viewModel.intentarCaptura() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lanzar pokebola")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.mensajeCaptura.isNotEmpty()) {
            Text(
                text = viewModel.mensajeCaptura,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF2F2F2),
                contentColor = Color(0xFF333333)
            ),
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}