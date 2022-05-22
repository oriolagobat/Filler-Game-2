/*
package com.example.filler.gui.preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {

    private var _binding: ActivityPreferencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        addFragment()
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
}
*/
