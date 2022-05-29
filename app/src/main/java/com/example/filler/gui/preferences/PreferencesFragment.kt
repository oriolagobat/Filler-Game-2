package com.example.filler.gui.preferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.filler.R
import com.example.filler.constants.gui.Music
import com.example.filler.gui.preferences.image.ImagePopup
import com.example.filler.gui.shared.SongPlayer
import com.example.filler.gui.shared.sound

class PreferencesFragment : PreferenceFragmentCompat() {
    private lateinit var imagePopup: ImagePopup

    override fun onAttach(context: Context) {
        super.onAttach(context)
        imagePopup = ImagePopup(requireContext() as PreferencesActivity)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        requireActivity().setTheme(R.style.PreferenceScreen)
        setPreferencesFromResource(R.xml.preferences, rootKey)
        setProfilePicListener()
        setMusicSwitchListener()
    }

    private fun setProfilePicListener() {
        findPreference<Preference>(getString(R.string.pref_profile_pic_key))
            ?.setOnPreferenceClickListener {
                imagePopup.show()
                true
            }
    }

    private fun setMusicSwitchListener() {
        findPreference<SwitchPreferenceCompat>(getString(R.string.pref_music_key))
            ?.setOnPreferenceChangeListener { _, newValue ->
                if (newValue as Boolean) startPreferenceSong()
                else stopPreferenceSong()
                true
            }
    }

    override fun onPause() {
        super.onPause()
        if (sound(requireContext())) stopPreferenceSong()
    }

    override fun onResume() {
        super.onResume()
        if (sound(requireContext())) startPreferenceSong()
    }

    private fun startPreferenceSong() {
        val intent = Intent(requireContext(), SongPlayer::class.java)
        intent.putExtra(Music.SONG.name, R.raw.preferences)
        intent.putExtra(Music.LOOP.name, true)
        requireActivity().startService(intent)
    }

    private fun stopPreferenceSong() {
        val intent = Intent(requireContext(), SongPlayer::class.java)
        requireActivity().stopService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        var imageString = imagePopup.chosenImageUri.toString()
        if (imageString == "gallery") {
            imageString = imagePopup.mediaGallery.imageUri!!
        }

        preferenceManager.sharedPreferences!!.edit()
            .putString(getString(R.string.pref_profile_pic_key), imageString)
            .apply()
    }
}
