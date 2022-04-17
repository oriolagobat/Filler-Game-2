package com.example.filler.gui.configuration

import android.content.Intent
import android.widget.Toast

class StartNewGame(
    context: NewGameConfiguration,

    username: String,
    colorNum: String,
    gridNum: String,
    timeControl: Boolean,
    difficulty: String
) {
    init {
        if (username.isEmpty()) {
            val error = "Please enter a username"
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        } else {
            val intent = newGameIntent(username, colorNum, gridNum, timeControl, difficulty)
            context.startActivity(intent)
        }
    }

    private fun newGameIntent(
        username: String,
        colorNum: String,
        gridNum: String,
        timeControl: Boolean,
        difficulty: String
    ): Intent {
        val intent = Intent()
        intent.putExtra("username", username)
        intent.putExtra("colorNum", getIntColorNum(colorNum))
        intent.putExtra("gridNum", getIntGridNum(gridNum))
        intent.putExtra("timeControl", timeControl)
        intent.putExtra("difficulty", difficulty)
        return intent
    }

    private fun getIntColorNum(colorNum: String): Int =
        colorNum.toInt()

    private fun getIntGridNum(gridNum: String): Int =
    // Return an integer of the first number
    // As it's square, we only neeed the first one
        // 3 X 3 -> 3
        gridNum.first().code
}