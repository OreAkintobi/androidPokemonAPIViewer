package com.ore.pokemongame.database_all_pokemon

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/** SHOWS WHERE POKEMON IS CREATED AND VARIABLES INITIALIZED FOR DATABASES **/
@Entity(tableName = "pokemon_table")
@Parcelize
data class Result(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String
) : Parcelable


data class AllPokemon(
    val count: Long,
    val next: String,
    val previous: Any? = null,
    val results: List<Result>
)