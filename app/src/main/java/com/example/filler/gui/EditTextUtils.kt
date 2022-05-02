package com.example.filler.gui

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R

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

fun getValidUsernameOrError(
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

fun getValidMailOrError(
    context: AppCompatActivity,
    editText: EditText,
): String {
    closeKeyboardClearFocus(context, editText)

    val mail = editText.text.toString()
    val defaultMail = context.getString(R.string.results_default_mail)
    // Not the default mail, must check
    if (mail != defaultMail && !validMail(mail)) {
        editText.error = "Please enter a valid email"
    }

    return editText.text.toString()
}

// Checks if a mail is valid
fun validMail(mail: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()