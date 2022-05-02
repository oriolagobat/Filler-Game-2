package com.example.filler.gui.configuration

import android.content.Intent
import android.widget.Toast
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding
import com.example.filler.gui.SongPlayer

fun setUpConfigListeners(
    context: NewGameConfiguration,
    binding: ActivityNewGameConfigurationBinding
) {
    // Set the onClickListener for the editText, checkBox and button
    binding.usernameInput.setOnClickListener(context)
    binding.timeCheckBox.setOnClickListener(context)
    binding.startNewGameButton.setOnClickListener(context)

    // Set the spinners onItemSelectedListener's
    binding.colorSpinner.onItemSelectedListener = context
    binding.gridSpinner.onItemSelectedListener = context

    // Set radioGroup's onCheckedChangeListener
    binding.difficultyRadioGroup.setOnCheckedChangeListener(context)
}

fun correctGameSettings(
    context: NewGameConfiguration,
    username: String?,
    difficulty: String?
): Boolean {
    val errorMsg: String? = when {
        // Check possible variables that don't have a default value
        (username == null) -> "Enter a username"
        (difficulty == null) -> "Select a difficulty"
        else -> null
    }

    if (errorMsg != null) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

fun startConfSong(context: NewGameConfiguration) {
    val intent = Intent(context, SongPlayer::class.java)
    intent.putExtra("song", R.raw.configuration)
    intent.putExtra("loop", true)  // Makes it loop while the user hasn't finished choosing
    context.startService(intent)
}

fun stopConfSong(context: NewGameConfiguration) {
    val intent = Intent(context, SongPlayer::class.java)
    context.stopService(intent)
}