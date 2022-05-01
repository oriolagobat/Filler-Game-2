package com.example.filler.gui

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

fun closeKeyboardClearFocus(
    context: AppCompatActivity,
    editText: EditText
) {
    val focused: View? = context.currentFocus
    focused?.let {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(focused.windowToken, 0)
    }
    // Clear the EditText focus
    editText.clearFocus()
}

fun getValidTextOrError(
    context: AppCompatActivity,
    editText: EditText,
): String {
    var input: String? = null

    if (editText.text.isEmpty()) {
        editText.error = "Please enter a username"
    } else {
        input = editText.text.toString()
    }
    closeKeyboardClearFocus(context, editText)

    // If the username is empty, return an empty username
    return input ?: ""
}

fun getText(
    context: AppCompatActivity,
    editText: EditText,
): String {
    closeKeyboardClearFocus(context, editText)
    return editText.text.toString()

}