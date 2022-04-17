package com.example.filler.gui.configuration

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.filler.databinding.ActivityNewGameConfigurationBinding

class NewUsernameInput(
    private val context: NewGameConfiguration,
    private val binding: ActivityNewGameConfigurationBinding
) {
    private var usernameInput: String? = null

    init {
        if (binding.usernameInput.text.isEmpty()) {
            binding.usernameInput.error = "Please enter a username"
        } else {
            usernameInput = binding.usernameInput.text.toString()
        }
        closeKeyboardClearFocus()
    }

    private fun closeKeyboardClearFocus() {
        val focused: View? = context.currentFocus
        focused?.let {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(focused.windowToken, 0)
        }
        // Clear the EditText focus
        binding.usernameInput.clearFocus()
    }

    fun get(): String {
        // If the username is empty, return an empty username
        return usernameInput ?: ""
    }
}