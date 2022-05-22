package com.example.filler.gui.preferences

import android.content.Context
import android.os.Bundle
import androidx.preference.*
import com.example.filler.R
import com.example.filler.constants.gui.*

class PreferencesFragment : PreferenceFragmentCompat() {
    private lateinit var screen: PreferenceScreen

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
            title = resources.getString(R.string.pref_game_category_title)
            screen.addPreference(this)
        }
    }

    private fun addBoardSizePreference(context: Context) {
        ListPreference(context).apply {
            key = BOARD_SIZE_KEY
            title = resources.getString(R.string.pref_game_board_title)
            summary = resources.getString(R.string.pref_game_board_summary)
            entries = resources.getStringArray(R.array.board_entries_array)
            entryValues = rangeToCharArray(BOARD_SIZE_MIN, BOARD_SIZE_MAX)
            setDefaultValue(BOARD_SIZE_DEFAULT.toString())
            screen.addPreference(this)
        }
    }

    // Returns an array of numeral characters from min to max
    private fun rangeToCharArray(minValue: Int, maxValue: Int): Array<out CharSequence> {
        return Array(maxValue - minValue + 1) { "" }.apply {
            for (i in minValue..maxValue)
                this[i - minValue] = i.toString()
        }
    }

    private fun addNumColorsPreference(context: Context) {
        ListPreference(context).apply {
            key = NUM_COLORS_KEY
            title = resources.getString(R.string.pref_game_colors_title)
            summary = resources.getString(R.string.pref_game_colors_summary)
            entries = resources.getStringArray(R.array.colors_entries_array)
            entryValues = rangeToCharArray(NUM_COLORS_MIN, NUM_COLORS_MAX)
            setDefaultValue(NUM_COLORS_DEFAULT.toString())
            screen.addPreference(this)
        }
    }

    private fun addDifficultyPreference(context: Context) {
        ListPreference(context).apply {
            key = DIFFICULTY_KEY
            title = resources.getString(R.string.pref_game_difficulty_title)
            summary = resources.getString(R.string.pref_game_difficulty_summary)
            entries = resources.getStringArray(R.array.difficulty_entries_array)
            entryValues = arrayOf(DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD)
            setDefaultValue(DIFFICULTY_DEFAULT)
            screen.addPreference(this)
        }
    }

    private fun addTimeControlPreference(context: Context) {
        SwitchPreferenceCompat(context).apply {
            key = TIME_CONTROL_KEY
            title = resources.getString(R.string.pref_game_time_control_title)
            summaryOn = resources.getString(R.string.pref_game_time_control_summary_on)
            summaryOff = resources.getString(R.string.pref_game_time_control_summary_off)
            isChecked = TIME_CONTROL_DEFAULT
            screen.addPreference(this)
        }
    }

    private fun addGeneralCategoryPreferences(context: Context) {
        addGeneralCategory(context)
        addAliasPreference(context)
        addProfilePicPreference(context)
        addMusicPreference(context)
    }

    private fun addGeneralCategory(context: Context) {
        PreferenceCategory(context).apply {
            title = resources.getString(R.string.pref_general_category_title)
            screen.addPreference(this)
        }
    }

    private fun addAliasPreference(context: Context) {
        EditTextPreference(context).apply {
            key = ALIAS_KEY
            title = resources.getString(R.string.pref_general_alias_title)
            summary = resources.getString(R.string.pref_general_alias_summary)
            setDefaultValue(ALIAS_DEFAULT)
            screen.addPreference(this)
        }
    }

    private fun addProfilePicPreference(context: Context) {
        Preference(context).apply {
            key = PROFILE_PIC_KEY
            title = resources.getString(R.string.pref_general_profile_pic_title)
            summary = resources.getString(R.string.pref_general_profile_pic_summary)
            setDefaultValue(R.drawable.user_profile)
            screen.addPreference(this)
        }
    }

    private fun addMusicPreference(context: Context) {
        SwitchPreferenceCompat(context).apply {
            key = MUSIC_KEY
            title = resources.getString(R.string.pref_general_music_title)
            summaryOn = resources.getString(R.string.pref_general_music_summary_on)
            summaryOff = resources.getString(R.string.pref_general_music_summary_off)
            isChecked = MUSIC_DEFAULT
            screen.addPreference(this)
        }
    }
}
