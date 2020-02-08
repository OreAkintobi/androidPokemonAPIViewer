package com.ore.pokemongame.database_all_pokemon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDAO


    companion object {
        private var INSTANCE: PokemonDatabase? = null
        fun getInstance(context: Context): PokemonDatabase? {
            if (INSTANCE == null) {
                synchronized(PokemonDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java, "pokemon-database"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}