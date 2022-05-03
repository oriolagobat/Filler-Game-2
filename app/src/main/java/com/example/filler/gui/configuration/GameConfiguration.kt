package com.example.filler.gui.configuration

import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding
import com.example.filler.gui.closeKeyboardClearFocus
import com.example.filler.gui.configuration.data.*
import java.io.Serializable

data class GameConfiguration(
    val username: Username = Username(),
    val colorNumber: ColorNumber = ColorNumber(),
    val boardSize: BoardSize = BoardSize(),
    val timeControl: TimeControl = TimeControl(),
    val difficultyString: DifficultyString = DifficultyString()
) : Serializable {
    fun spinnerClick(spinner: AdapterView<*>?, position: Int) {
        // Don't ignore first call, it will serve as the default if user doesn't select anything
        when (spinner?.id) {
            R.id.colorSpinner -> colorNumber.value = getItemFromSpinner(spinner, position)
            R.id.gridSpinner -> boardSize.value = getItemFromSpinner(spinner, position)
        }
    }

    private fun getItemFromSpinner(spinner: AdapterView<*>, position: Int): String =
        spinner.getItemAtPosition(position).toString()

    // Manages clicks on the editText, checkBox and button
    fun click(
        v: View?,
        context: NewGameConfiguration,
        binding: ActivityNewGameConfigurationBinding
    ) {
        when (v?.id) {
            R.id.usernameInput -> manageUsernameInput(context, binding)
            R.id.timeCheckBox -> manageTimeCheckBox(binding)
            R.id.startNewGameButton -> manageNewGameButton(context)
        }
    }

    // Manages the username input EditText
    private fun manageUsernameInput(
        context: NewGameConfiguration,
        binding: ActivityNewGameConfigurationBinding
    ) {
        val usernameInput = binding.usernameInput
        if (usernameInput.text!!.isEmpty()) {
            usernameInput.error = "Please enter a username"
        } else {
            username.value = usernameInput.text.toString()
        }
        closeKeyboardClearFocus(context, usernameInput)
    }

    // Manages time checkBox
    private fun manageTimeCheckBox(binding: ActivityNewGameConfigurationBinding) {
        this.timeControl.value = binding.timeCheckBox.isChecked
    }

    // Manages new game button
    private fun manageNewGameButton(context: NewGameConfiguration) {
        if (correctGameSettings(context, username, difficultyString)) {
            StartNewGame(this, context)
        }
    }

    fun radioButtonClick(context: NewGameConfiguration, checkedId: Int) {
        val checked: RadioButton = context.findViewById(checkedId)
        this.difficultyString.value = checked.text.toString()
    }
}