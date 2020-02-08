package com.ore.pokemongame.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.ore.pokemongame.R
import com.ore.pokemongame.adapters.PokemonAdapter
import com.ore.pokemongame.models.Pokemon
import kotlinx.android.synthetic.main.activity_view_pokemon.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ViewPokemonActivity : AppCompatActivity() {
    var pokemonName: String? = ""
    var pokemonId: Int = 0
    //    val pokemon = Pokemon().abilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pokemon)
        pokemonName = intent.getStringExtra("Pokemon")
        pokemonId = intent.getIntExtra("id", 0)
        val actionBar = supportActionBar
        actionBar!!.title = "Pokemon No. $pokemonId - $pokemonName"

        if (pokemonName != null && pokemonId != 0) {
            fetchPokemon()
        }
    }

    private fun fetchPokemon() {
        val url = "https://pokeapi.co/api/v2/pokemon/${pokemonId}/"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)
                Log.d("POKEMON", body!!)

                val gson = GsonBuilder().create()
                val Pokemon = gson.fromJson(body, Pokemon::class.java)

                runOnUiThread {
                    recycler_view_pokemon.adapter =
                        PokemonAdapter(Pokemon)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute ${e.printStackTrace()}")
            }
        })
    }
}
