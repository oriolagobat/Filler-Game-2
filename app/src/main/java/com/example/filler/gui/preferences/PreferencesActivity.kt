package com.example.filler.gui.preferences

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.constants.gui.Music
import com.example.filler.databinding.ActivityPreferencesBinding
import com.example.filler.gui.shared.SongPlayer
import com.example.filler.gui.shared.sound

class PreferencesActivity : AppCompatActivity() {

    private var _binding: ActivityPreferencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startPreferenceSong()
        initUI()
        addFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPreferenceSong()
    }

    private fun initUI() {
        _binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun addFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preferences_frag_container, PreferencesFragment())
            .commit()
    }

    private fun startPreferenceSong() {
        if (!sound(this)) return
        val intent = Intent(this, SongPlayer::class.java)
        intent.putExtra(Music.SONG.name, R.raw.preferences)
        intent.putExtra(Music.LOOP.name, true)
        startService(intent)
    }

    private fun stopPreferenceSong() {
        if (!sound(this)) return
        val intent = Intent(this, SongPlayer::class.java)
        stopService(intent)
    }
}
