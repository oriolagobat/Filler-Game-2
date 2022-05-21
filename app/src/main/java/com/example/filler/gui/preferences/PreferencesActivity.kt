/*
package com.example.filler.gui.preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        addFragment()
    }

    private fun initUI() {
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
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
