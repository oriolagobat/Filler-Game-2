package com.example.filler.gui.configuration

import android.content.Intent
import android.widget.Toast
import com.example.filler.R
import com.example.filler.constants.gui.Intents
import com.example.filler.gui.game.GUIGame
import com.example.filler.log.Logger

class StartNewGame(
    private val settings: GameConfiguration,
    private val context: NewGameConfiguration
) {
    init {
        if (settings.username.value!!.isEmpty()) {
            val error = context.getString(R.string.username_empty_toast)
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        } else {
            // Stop the song player
            stopConfSong(context)

            val intent = buildNewGameIntent()
            Logger.clearLog()
            context.startActivity(intent)
            context.finish()
        }
    }

    private fun buildNewGameIntent(
    ): Intent {
        // Save the chosen image, done here because image is chosen asynchronously
        settings.profilePicture.value = context.imagePopup.chosenImageUri.toString()
        val intent = Intent(context, GUIGame::class.java)
        intent.putExtra(Intents.SETTINGS.name, settings)
        return intent
    }
}
