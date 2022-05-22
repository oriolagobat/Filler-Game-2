/*
package com.example.filler.gui.preferences

import android.content.Context
import android.os.Bundle
import androidx.preference.*
import com.example.filler.constants.gui.*

//TODO: Replace with call to arrays on resources.
val NUM_COLORS_ENTRIES = arrayOf("3", "4", "5", "6", "7", "8")
val BOARD_SIZE_ENTRIES = arrayOf("3 x 3", "4 x 4", "5 x 5", "6 x 6", "7 x 7", "8 x 8", "9 x 9")
val DIFFICULTY_ENTRIES = arrayOf("Easy", "Medium", "Hard")

class PreferencesFragment : PreferenceFragmentCompat() {
    private lateinit var screen: PreferenceScreen;

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        initPreferenceScreen()
        buildPreferenceScreen(screen.context)
        preferenceScreen = screen
    }

    private fun initPreferenceScreen() {
        val context = preferenceManager.context
        screen = preferenceManager.createPreferenceScreen(context)
    }

    private fun buildPreferenceScreen(context: Context) {
        addGameCategoryPreferences(context)
        addGeneralCategoryPreferences(context)
    }

    private fun addGameCategoryPreferences(context: Context) {
        addGameCategory(context)
        addBoardSizePreference(context)
        addNumColorsPreference(context)
        addDifficultyPreference(context)
        addTimeControlPreference(context)
    }

    private fun addGameCategory(context: Context) {
        PreferenceCategory(context).apply {
            title = "Game"
            screen.addPreference(this)
        }
    }

    private fun addBoardSizePreference(context: Context) {
        ListPreference(context).apply {
            key = BOARD_SIZE_KEY
            title = "Board size"
            summary = "Choose the size of the board"
            entries = BOARD_SIZE_ENTRIES
            entryValues = rangetoCharArray(BOARD_SIZE_MIN, BOARD_SIZE_MAX)
            setDefaultValue(BOARD_SIZE_DEFAULT.toString())
            screen.addPreference(this)
        }
    }

    // Returns an array of numeral characters from min to max
    private fun rangetoCharArray(minValue: Int, maxValue: Int): Array<out CharSequence> {
        return Array(maxValue - minValue + 1) { "" }.apply {
            for (i in minValue..maxValue)
                this[i - minValue] = i.toString()
        }
    }

    private fun addNumColorsPreference(context: Context) {
        ListPreference(context).apply {
            key = NUM_COLORS_KEY
            title = "Colors"
            summary = "Choose the number of colors"
            entries = NUM_COLORS_ENTRIES
            entryValues = rangetoCharArray(NUM_COLORS_MIN, NUM_COLORS_MAX)
            this.setDefaultValue(NUM_COLORS_DEFAULT.toString())
            screen.addPreference(this)
        }
    }

    private fun addDifficultyPreference(context: Context) {
        ListPreference(context).apply {
            key = DIFFICULTY_KEY
            title = "Difficulty"
            summary = "Choose the difficulty"
            entries = DIFFICULTY_ENTRIES
            entryValues = arrayOf(DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD)
            this.setDefaultValue(DIFFICULTY_DEFAULT)
            screen.addPreference(this)
        }
    }

    private fun addTimeControlPreference(context: Context) {
        SwitchPreferenceCompat(context).apply {
            key = TIME_CONTROL_KEY
            title = "Time Control"
            summaryOn = "Time Control On"
            summaryOff = "Time Control Off"
            isChecked = TIME_CONTROL_DEFAULT
            screen.addPreference(this)
        }
    }

    private fun addGeneralCategoryPreferences(context: Context) {
        addGeneralCategory(context)
        addAliasPreference(context)
//        addProfilePicPreference(context)
        addMusicPreference(context)
    }

    private fun addGeneralCategory(context: Context) {
        PreferenceCategory(context).apply {
            title = "General"
            screen.addPreference(this)
        }
    }

    private fun addAliasPreference(context: Context) {
        EditTextPreference(context).apply {
            key = ALIAS_KEY
            title = "Alias"
            summary = "Choose your alias"
            this.setDefaultValue(ALIAS_DEFAULT)
            screen.addPreference(this)
        }
    }

    private fun addMusicPreference(context: Context) {
        SwitchPreferenceCompat(context).apply {
            key = MUSIC_KEY
            title = "Music"
            summaryOn = "Music On"
            summaryOff = "Music Off"
            isChecked = MUSIC_DEFAULT
            screen.addPreference(this)
        }
    }
}*/
