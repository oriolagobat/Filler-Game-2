package com.example.filler.gui.configuration

import android.content.Intent
import android.widget.Toast
import com.example.filler.R
import com.example.filler.constants.gui.Music
import com.example.filler.databinding.ActivityNewGameConfigurationBinding
import com.example.filler.gui.configuration.data.DifficultyString
import com.example.filler.gui.configuration.data.ProfilePicture
import com.example.filler.gui.configuration.data.Username
import com.example.filler.gui.shared.SongPlayer

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
    binding.boardSpinner.onItemSelectedListener = context

    // Set radioGroup's onCheckedChangeListener
    binding.difficultyRadioGroup.setOnCheckedChangeListener(context)
}

fun correctGameSettings(
    context: NewGameConfiguration,
    username: Username,
    difficulty: DifficultyString
): Boolean {
    val errorMsg: String? = when {
        // Check possible variables that don't have a default value
        (username.value == null) -> context.getString(R.string.username_empty)
        (difficulty.value == null) -> context.getString(R.string.difficulty_empty)
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
    intent.putExtra(Music.SONG.name, R.raw.configuration)
    intent.putExtra(Music.LOOP.name, true)  // Makes it loop while the user hasn't finished choosing
    context.startService(intent)
}

fun stopConfSong(context: NewGameConfiguration) {
    val intent = Intent(context, SongPlayer::class.java)
    context.stopService(intent)
}

fun savePlayerImage(
    profilePicture: ProfilePicture,
    context: NewGameConfiguration
) {
    val savedImageUriStr = context.imagePopup.chosenImageUri.toString()

    if (userChoseLibrary(savedImageUriStr)) saveLibraryUriStr(
        profilePicture,
        context
    ) else saveUriStr(profilePicture, savedImageUriStr)
}

private fun userChoseLibrary(savedImageUriStr: String): Boolean = (savedImageUriStr == "gallery")
private fun saveLibraryUriStr(
    profilePicture: ProfilePicture, context: NewGameConfiguration
) {
    profilePicture.value = context.imagePopup.mediaGallery.imageUri!!.toString()
}
private fun saveUriStr(profilePicture: ProfilePicture, savedImageUriStr: String) {
    profilePicture.value = savedImageUriStr
}