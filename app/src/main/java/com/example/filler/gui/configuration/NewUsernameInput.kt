package com.example.filler.gui.configuration

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.filler.databinding.ActivityNewGameConfigurationBinding

class NewUsernameInput(
    private val context: NewGameConfiguration,
    private val usernameEditText: EditText
) {
    private var usernameInput: String? = null

    private fun closeKeyboardClearFocus() {
        val focused: View? = context.currentFocus
        focused?.let {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(focused.windowToken, 0)
        }
        // Clear the EditText focus
        usernameEditText.clearFocus()
    }

    fun get(): String {
        if (usernameEditText.text.isEmpty()) {
            usernameEditText.error = "Please enter a username"
        } else {
            usernameInput = usernameEditText.text.toString()
        }
        closeKeyboardClearFocus()

        // If the username is empty, return an empty username
        return usernameInput ?: ""
    }
}
