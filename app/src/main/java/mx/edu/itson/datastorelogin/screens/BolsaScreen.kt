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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.itson.datastorelogin.navigation.Screen
import mx.edu.itson.datastorelogin.viewModel.PokemonViewModel

@Composable
fun BolsaScreen(
    navController: NavController,
    viewModel: PokemonViewModel
){
    LaunchedEffect(Unit) {
        viewModel.cargarPokemones()
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Pokemones capturados",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screen.Capturar.route)}
        ){
            Text(
                text = "Atrapar un pokemon"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.busqueda,
            onValueChange = {
                viewModel.busqueda = it
                viewModel.cargarPokemones()
            },
            label = {Text(text = "Buscar por nombre")}
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val tipos = listOf("Todos", "Fuego", "Agua", "Planta")
            tipos.forEach { tipo ->
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.typeSelected = tipo
                        viewModel.cargarPokemones()
                    }
                ) {
                    Text(text = tipo, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.pokemones) { p ->
                Card(modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "#${p.number} - ${p.name}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(text = "Tipo: ${p.type} | Nivel: ${p.level}")

                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            Button(onClick = { viewModel.tryLevelUp(p) }) {
                                Text("Subir Nivel")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = { viewModel.deletePokemon(p) }
                            ) {
                                Text("Liberar")
                            }
                        }
                    }
                }
            }
        }
    }
}