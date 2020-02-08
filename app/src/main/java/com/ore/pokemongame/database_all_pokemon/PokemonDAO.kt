package com.ore.pokemongame.database_all_pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon_table")
    fun getAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(result: Result)

    @Insert
    fun insertAll(vararg pokemon: Result)

    @Query("DELETE FROM pokemon_table")
    fun deleteAll()
}