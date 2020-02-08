package com.ore.pokemongame.models


import kotlinx.serialization.SerialName


data class Pokemon(
    val abilities: List<Ability>,

    @SerialName("base_experience")
    val baseExperience: Long,

    val forms: List<Species>,

    @SerialName("game_indices")
    val gameIndices: List<GameIndex>,

    val height: Long,

    @SerialName("held_items")
    val heldItems: List<Any?>,

    val id: Long,

    @SerialName("is_default")
    val isDefault: Boolean,

    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,

    val moves: List<Move>,
    val name: String,
    val order: Long,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long
)

data class Ability(
    val ability: Species,

    @SerialName("is_hidden")
    val isHidden: Boolean,

    val slot: Long
)

data class Species(
    val name: String,
    val url: String
)

data class GameIndex(
    @SerialName("game_index")
    val gameIndex: Long,

    val version: Species
)

data class Move(
    val move: Species,

    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)

data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Long,

    @SerialName("move_learn_method")
    val moveLearnMethod: Species,

    @SerialName("version_group")
    val versionGroup: Species
)

data class Sprites(
    @SerialName("back_default")
    val backDefault: String,

    @SerialName("back_female")
    val backFemale: Any? = null,

    @SerialName("back_shiny")
    val backShiny: String,

    @SerialName("back_shiny_female")
    val backShinyFemale: Any? = null,

    @SerialName("front_default")
    val frontDefault: String,

    @SerialName("front_female")
    val frontFemale: Any? = null,

    @SerialName("front_shiny")
    val frontShiny: String,

    @SerialName("front_shiny_female")
    val frontShinyFemale: Any? = null
)

data class Stat(
    @SerialName("base_stat")
    val baseStat: Long,

    val effort: Long,
    val stat: Species
)

data class Type(
    val slot: Long,
    val type: Species
)

