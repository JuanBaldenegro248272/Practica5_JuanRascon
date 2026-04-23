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
    var pokemonSalvaje by mutableStateOf<PokemonEntity?>(null)
    var mensajeCaptura by mutableStateOf("")
    var mensajeNivel by mutableStateOf("")
    var nivelMinimo by mutableStateOf(1f)

    private val pokemonesDisponibles = listOf(
        PokemonEntity(name = "Pikachu", number = "025", type = "Electrico"),
        PokemonEntity(name = "Charmander", number = "004", type = "Fuego"),
        PokemonEntity(name = "Squirtle", number = "007", type = "Agua"),
        PokemonEntity(name = "Bulbasaur", number = "001", type = "Planta"),
        PokemonEntity(name = "Pidgey", number = "016", type = "Normal")
    )

    fun cargarPokemones() {
        viewModelScope.launch {
            repository.getPokemonFilter(
                busqueda,
                typeSelected,
                nivelMinimo.toInt()
            ).collect { lista ->
                pokemones = lista
            }
        }
    }

    fun buscarPokemonSalvaje() {
        pokemonSalvaje = pokemonesDisponibles.random()
        mensajeCaptura = ""
    }

    fun intentarCaptura() {
        val pokemon = pokemonSalvaje ?: return

        if (Random.nextBoolean()) {
            viewModelScope.launch {
                repository.add(
                    PokemonEntity(
                        name = pokemon.name,
                        number = pokemon.number,
                        type = pokemon.type,
                        level = 1
                    )
                )
                mensajeCaptura = "Atrapaste a ${pokemon.name}"
                pokemonSalvaje = null
                cargarPokemones()
            }
        } else {
            mensajeCaptura = "${pokemon.name} se escapó"
            pokemonSalvaje = null
        }
    }

    fun deletePokemon(pokemon: PokemonEntity) {
        viewModelScope.launch {
            repository.delete(pokemon)
            cargarPokemones()
        }
    }

    fun tryLevelUp(pokemon: PokemonEntity) {
        if (pokemon.level >= 100) {
            mensajeNivel = "${pokemon.name} ya no puede subir de nivel, ya es toda wey"
            return
        }
        if (Random.nextBoolean()) {
            viewModelScope.launch {
                repository.update(pokemon.copy(level = pokemon.level + 1))
                mensajeNivel = "${pokemon.name} subio al nivel ${pokemon.level + 1}"
                cargarPokemones()
            }
        } else {
            mensajeNivel = "${pokemon.name} Mala suerte, tu pokemon no subio de nivel, prueba de nuevo"
        }
    }
}