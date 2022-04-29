package com.example.filler.gui.results

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class NewEmailInput(private val context: Results, private val emailInput: EditText) {
    private fun closeKeyboardClearFocus() {
        val focused: View? = context.currentFocus
        focused?.let {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(focused.windowToken, 0)
        }
        // Clear the EditText focus
        emailInput.clearFocus()
    }

    fun get(): String {
        closeKeyboardClearFocus()
        return emailInput.text.toString()
    }
}