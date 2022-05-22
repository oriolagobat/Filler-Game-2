package com.example.filler.gui.shared

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.preference.PreferenceManager
import com.example.filler.R

fun hideNavBar(activity: Activity) {
    val window: Window = activity.window
    val decorView: View = activity.window.decorView

    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controllerCompat = WindowInsetsControllerCompat(window, decorView)
    controllerCompat.hide(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.navigationBars())
    controllerCompat.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

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
private fun validMail(mail: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()

// Returns the preferences manager
fun getPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

// Returns true if the user wants music
fun sound(context: Context): Boolean {
    val sharedPreferences = getPreferences(context)
    return sharedPreferences.getBoolean("music", true) // Music on by default
}