package com.example.filler.gui.configuration

import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding
import com.example.filler.gui.configuration.data.*
import com.example.filler.gui.game.image.ImagePopup
import com.example.filler.gui.shared.closeKeyboardClearFocus
import com.example.filler.log.Logger
import java.io.Serializable

data class GameConfiguration(
    val username: Username = Username(),
    val colorNumber: ColorNumber = ColorNumber(),
    val boardSize: BoardSize = BoardSize(),
    val timeControl: TimeControl = TimeControl(),
    val difficultyString: DifficultyString = DifficultyString(),
    val profilePicture: ProfilePicture = ProfilePicture(),
) : Serializable {
    fun spinnerClick(spinner: AdapterView<*>?, position: Int) {
        when (spinner?.id) {
            R.id.colorSpinner -> colorSpinnerClick(spinner, position)
            R.id.boardSpinner -> boardSizeClick(spinner, position)
        }
    }

    private fun colorSpinnerClick(spinner: AdapterView<*>?, position: Int) {
        colorNumber.value = getItemFromSpinner(spinner, position)
        Logger.logInfo("User chose to use ${colorNumber.value}")
    }

    private fun boardSizeClick(spinner: AdapterView<*>?, position: Int) {
        boardSize.value = getItemFromSpinner(spinner, position)
        Logger.logInfo("User chose board size: ${boardSize.value}")
    }

    private fun getItemFromSpinner(spinner: AdapterView<*>?, position: Int): String =
        spinner?.getItemAtPosition(position).toString()

    fun click(
        v: View?,
        context: NewGameConfiguration,
        binding: ActivityNewGameConfigurationBinding,
    ) {
        when (v?.id) {
            R.id.usernameInput -> manageUsernameInput(context, binding)
            R.id.timeCheckBox -> manageTimeCheckBox(binding)
            R.id.startNewGameButton -> manageNewGameButton(context)
        }
    }

    private fun manageUsernameInput(
        context: NewGameConfiguration,
        binding: ActivityNewGameConfigurationBinding
    ) {
        val usernameInput = binding.usernameInput
        if (usernameInput.text!!.isEmpty()) {
            usernameInput.error = context.getString(R.string.username_empty)
        } else {
            username.value = usernameInput.text.toString()
            Logger.logInfo("User chose username: ${username.value}")
        }
        closeKeyboardClearFocus(context, usernameInput)
    }

    private fun manageTimeCheckBox(binding: ActivityNewGameConfigurationBinding) {
        this.timeControl.value = binding.timeCheckBox.isChecked
        Logger.logInfo("User chose time control: ${timeControl.value}")
    }

    fun radioButtonClick(context: NewGameConfiguration, checkedId: Int) {
        val checked: RadioButton = context.findViewById(checkedId)
        this.difficultyString.value = getDifficultyString(checked.id)
        Logger.logInfo("User chose difficulty: ${difficultyString.value}")
    }

    private fun getDifficultyString(checkedId: Int): String {
        return when (checkedId) {
            R.id.difficultyEasy -> "easy"
            R.id.difficultyMedium -> "medium"
            R.id.difficultyHard -> "hard"
            else -> throw IllegalStateException("Unknown radio button id: $checkedId")
        }
    }

    private fun manageNewGameButton(context: NewGameConfiguration) {
        if (correctGameSettings(context, username, difficultyString)) {
            StartNewGame(this, context)
            Logger.logInfo("User chooses to start a new game")
        }
    }
}