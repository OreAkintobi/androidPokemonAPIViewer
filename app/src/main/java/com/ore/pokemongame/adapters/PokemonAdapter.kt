package com.ore.pokemongame.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ore.pokemongame.R
import com.ore.pokemongame.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_single_view.view.*

class PokemonAdapter(private val pokemon: Pokemon) : RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val pokemonView = layoutInflater.inflate(R.layout.pokemon_single_view, parent, false)
        return PokemonViewHolder(pokemonView)


    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val view = holder.view

        view.pokemon_abilities2.text = "${pokemon.abilities[0].ability.name.capitalize()}"
        view.pokemon_height2.text = "${pokemon.height}"
        view.pokemon_moves2.text = "${pokemon.moves[0].move.name.capitalize()}"
        view.pokemon_weight2.text = "${pokemon.weight}"
        view.pokemon_species2.text = "${pokemon.species.name.capitalize()}"
        view.pokemon_type2.text = "${pokemon.types[0].type.name.capitalize()}"

        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/${pokemon.id}.png")
            .placeholder(
                R.drawable.pokeball1
            )
            .error(R.drawable.pokeball1).into(view.image_back_default)
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/${pokemon.id}.png")
            .placeholder(
                R.drawable.pokeball1
            )
            .error(R.drawable.pokeball1).into(view.image_back_shiny)
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png")
            .placeholder(
                R.drawable.pokeball1
            )
            .error(R.drawable.pokeball1).into(view.image_front_default)
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokemon.id}.png")
            .placeholder(
                R.drawable.pokeball1
            )
            .error(R.drawable.pokeball1).into(view.image_front_shiny)
    }
}

class PokemonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
}
