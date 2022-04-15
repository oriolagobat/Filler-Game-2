package com.example.filler.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding

class NewGameConfiguration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_configuration)

        // Set this class binding
        val binding = ActivityNewGameConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
    }
}