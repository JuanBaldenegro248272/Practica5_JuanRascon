package mx.edu.itson.datastorelogin.data

import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDAO: PokemonDAO){
    val allPokemons = pokemonDAO.getAllPokemon()

    suspend fun add(pokemon: PokemonEntity){
        pokemonDAO.addPokemon(pokemon)
    }

    suspend fun update(pokemon: PokemonEntity){
        pokemonDAO.update(pokemon)
    }

    suspend fun delete(pokemon: PokemonEntity){
        pokemonDAO.delete(pokemon)
    }

    fun getPokemonFilter(searchInput: String, type: String, level: Int): Flow<List<PokemonEntity>> {
        val search = "%$searchInput%"
        return pokemonDAO.getFilteredPokemones(search, type, level)
    }
}