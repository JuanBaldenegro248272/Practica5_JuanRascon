package mx.edu.itson.datastorelogin.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.itson.datastorelogin.data.PokemonEntity
import mx.edu.itson.datastorelogin.data.PokemonRepository
import kotlin.random.Random

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    var pokemones by mutableStateOf(listOf<PokemonEntity>())
    var busqueda by mutableStateOf("")
    var typeSelected by mutableStateOf("Todos")
    fun cargarPokemones() {
        viewModelScope.launch {
            repository.getPokemonFilter(busqueda, typeSelected, 1).collect { nuevaLista ->
                pokemones = nuevaLista
            }
        }
    }

    fun insertPokemon(pokemon: PokemonEntity) {
        viewModelScope.launch {
            repository.add(pokemon)
            cargarPokemones()
        }
    }

    fun deletePokemon(pokemon: PokemonEntity) {
        viewModelScope.launch {
            repository.delete(pokemon)
            cargarPokemones()
        }
    }

    fun tryLevelUp(pokemon: PokemonEntity) {
        if (pokemon.level >= 100) return
        if (Random.nextBoolean()) {
            viewModelScope.launch {
                val clon = pokemon.copy(level = pokemon.level + 1)
                repository.update(clon)
                cargarPokemones()
            }
        }
    }
}