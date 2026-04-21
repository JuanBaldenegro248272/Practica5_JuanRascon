package mx.edu.itson.datastorelogin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.itson.datastorelogin.data.PokemonEntity
import mx.edu.itson.datastorelogin.viewModel.PokemonViewModel

@Composable
fun CapturarScreen(
    navController: NavController,
    viewModel: PokemonViewModel
){
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Atrapar un Pokemon",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = number,
            onValueChange = {number = it},
            label = {Text(
             text = "Name"
            ) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {name = it},
            label = {Text(
                text = "Name"
            )}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = type,
            onValueChange = {type = it},
            label = {Text(
                text = "Type"
            )})

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (!name.isEmpty() && !type.isEmpty()){
                viewModel.insertPokemon(PokemonEntity(name = name, number = number,type = type,level = 1))
                navController.popBackStack()
            }
        }) {
            Text(
                text = "Guardar"
            )
        }
    }
}