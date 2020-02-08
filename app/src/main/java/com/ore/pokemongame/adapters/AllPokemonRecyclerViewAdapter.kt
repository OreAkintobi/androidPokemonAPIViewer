package com.ore.pokemongame.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ore.pokemongame.R
import com.ore.pokemongame.activities.ViewPokemonActivity
import com.ore.pokemongame.database_all_pokemon.Result
import com.ore.pokemongame.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_item.view.*
import java.util.*

class AllPokemonRecyclerViewAdapter(
    private val allPokemon: ArrayList<Result>,
    var context: Context
) : RecyclerView.Adapter<CustomViewHolder>() {
    private var results = emptyList<Pokemon>()

    override fun getItemCount(): Int {
        return allPokemon.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowItem = layoutInflater.inflate(R.layout.pokemon_item, parent, false)
        return CustomViewHolder(rowItem)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val result = allPokemon[position]
        holder.view.internet_pokemon_name.text = result.name.capitalize()
        holder.view.internet_pokemon_number.text = "${position + 1}"
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ViewPokemonActivity::class.java)
            intent.putExtra("Pokemon", result.name.capitalize())
            intent.putExtra("id", position + 1)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivities(arrayOf(intent))
        }
        val bookThumbnail = holder.view.internet_pokemon_image
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position + 1}.png")
            .placeholder(
                R.drawable.pokeball1
            )
            .error(R.drawable.pokeball1).into(bookThumbnail)
    }

    internal fun setPokemon(pokemon: List<Pokemon>) {
        this.results = pokemon
        notifyDataSetChanged()
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}