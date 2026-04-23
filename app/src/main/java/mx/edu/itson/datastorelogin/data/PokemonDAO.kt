package mx.edu.itson.datastorelogin.data

import androidx.core.content.MimeTypeFilter
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon_table ORDER BY number ASC")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemon(pokemon: PokemonEntity)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Update
    suspend fun update(pokemon: PokemonEntity)

    @Query("""
    SELECT * FROM pokemon_table
    WHERE name LIKE :searchQuery
    AND (:typeFilter = 'Todos' OR type = :typeFilter)
    AND level >= :level1
    ORDER BY number ASC
    """)
    fun getFilteredPokemones(
        searchQuery: String,
        typeFilter: String,
        level1: Int
    ): Flow<List<PokemonEntity>>
}