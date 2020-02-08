package com.ore.pokemongame.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.ore.pokemongame.R
import com.ore.pokemongame.adapters.AllPokemonRecyclerViewAdapter
import com.ore.pokemongame.database_all_pokemon.AllPokemon
import com.ore.pokemongame.database_all_pokemon.PokemonDatabase
import com.ore.pokemongame.database_all_pokemon.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var db: PokemonDatabase
    private lateinit var list: ArrayList<Result>
    private lateinit var roomPokemonAdapter: AllPokemonRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = PokemonDatabase.getInstance(this)!!
        fetchAllPokemon()
    }

    private fun fetchAllPokemon() {
        db = PokemonDatabase.getInstance(this)!!
        val url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=649"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)
                Log.d("POKEMON", body!!)

                val gson = GsonBuilder().create()
                val allPokemon = gson.fromJson(body, AllPokemon::class.java)

                for (pokemon in allPokemon.results) {
                    db.pokemonDao().deleteAll()
                }
                for (pokemon in allPokemon.results) {
                    db.pokemonDao().insert(pokemon)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute ${e.printStackTrace()}")
//                    main_recycler_view.visibility = View.GONE
//                    main_text_view.visibility = View.VISIBLE
//                    main_text_view.text = "Sorry, nothing to see here"
            }
        })
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            list = loadAllPokemon()
            Log.e("PokemonList: ", list.toString())
            roomPokemonAdapter = AllPokemonRecyclerViewAdapter(list, applicationContext)
            main_recycler_view.adapter = roomPokemonAdapter
        }
    }

    suspend fun loadAllPokemon(): ArrayList<Result> {
        return GlobalScope.async(Dispatchers.IO) {
            val pokemonDB = db.pokemonDao().getAll() as ArrayList<Result>
            pokemonDB
        }.await()
    }
}

