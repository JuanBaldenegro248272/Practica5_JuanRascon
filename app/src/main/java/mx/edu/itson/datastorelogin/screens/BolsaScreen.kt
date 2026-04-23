package mx.edu.itson.datastorelogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.itson.datastorelogin.navigation.Screen
import mx.edu.itson.datastorelogin.viewModel.PokemonViewModel

@Composable
fun BolsaScreen(
    navController: NavController,
    viewModel: PokemonViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.cargarPokemones()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bolsa de Pokémon",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screen.Capturar.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD96C6C), contentColor = Color.White),
        ) {
            Text("Ir a capturar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.busqueda,
            onValueChange = {
                viewModel.busqueda = it
                viewModel.cargarPokemones()
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Buscar por nombre") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Nivel mínimo: ${viewModel.nivelMinimo.toInt()}",
            style = MaterialTheme.typography.bodyMedium
        )

        Slider(
            value = viewModel.nivelMinimo,
            onValueChange = {
                viewModel.nivelMinimo = it
                viewModel.cargarPokemones()
            },
            valueRange = 1f..100f,
            steps = 98,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val tipos = listOf("Todos", "Fuego", "Agua", "Planta", "Electrico")
            items(tipos) { tipo ->
                Button(
                    onClick = {
                        viewModel.typeSelected = tipo
                        viewModel.cargarPokemones()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF2F2F2),
                        contentColor = Color(0xFF333333)
                    )
                ) {
                    Text(tipo)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(viewModel.pokemones) { pokemon ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "${pokemon.name} - Nivel ${pokemon.level}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text("No. ${pokemon.number}")
                        Text("Tipo: ${pokemon.type}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD96C6C),
                                contentColor = Color.White),
                                onClick = { viewModel.tryLevelUp(pokemon) }) {
                                Text("Subir nivel")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF2F2F2),
                                    contentColor = Color(0xFF333333)),
                                onClick = { viewModel.deletePokemon(pokemon) }) {
                                Text("Liberar")
                            }
                        }
                    }
                }
            }
        }
    }
}