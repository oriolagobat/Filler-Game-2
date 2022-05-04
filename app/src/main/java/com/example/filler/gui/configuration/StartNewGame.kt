package com.example.filler.gui.configuration

import android.content.Intent
import android.widget.Toast
import com.example.filler.constants.gui.Intents
import com.example.filler.gui.game.GUIGame

class StartNewGame(
    private val settings: GameConfiguration,
    private val context: NewGameConfiguration
) {
    init {
        if (settings.username.value!!.isEmpty()) {
            val error = "Please enter a username"
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        } else {
            // Stop the song player
            stopConfSong(context)

            val intent = buildNewGameIntent()
            context.startActivity(intent)
        }
    }

    private fun buildNewGameIntent(
    ): Intent {
        val intent = Intent(context, GUIGame::class.java)
        intent.putExtra(Intents.SETTINGS.name, settings)
        return intent
    }
}
