package com.ore.pokemongame.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ore.pokemongame.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        supportActionBar?.hide()

        GlobalScope.launch(Dispatchers.Main) {
            goToMain()
        }
    }

    private suspend fun goToMain() {
        delay(3000)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent, null)
    }
}
