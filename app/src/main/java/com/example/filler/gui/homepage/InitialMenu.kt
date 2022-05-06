package com.example.filler.gui.homepage

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.filler.R
import com.example.filler.databinding.ActivityMainBinding
import com.example.filler.gui.configuration.NewGameConfiguration
import com.example.filler.gui.help.Help
import com.example.filler.gui.shared.hideNavBar
import com.google.android.material.color.DynamicColors

class InitialMenu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request permissions
        checkAndRequestInitialPermissions()

        // Set the dynamic colors
        DynamicColors.applyToActivitiesIfAvailable(application)

        // Hide the navbar
        hideNavBar(this)

        // Get the binding and set the view
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set listeners to the buttons
        binding.helpButton.setOnClickListener(this)
        binding.newGameButton.setOnClickListener(this)
        binding.quitButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.helpButton -> startHelpScreen()
            R.id.newGameButton -> startConfigurationScreen()
            R.id.quitButton -> finish()
            else -> throw IllegalArgumentException("No more buttons")
        }
    }

    private fun startHelpScreen() {
        val helpIntent = Intent(this, Help::class.java)
        startActivity(helpIntent)
    }

    private fun startConfigurationScreen() {
        val configurationIntent = Intent(this, NewGameConfiguration::class.java)
        startActivity(configurationIntent)
        finish()
    }

    private fun checkAndRequestInitialPermissions() {
        ActivityCompat.requestPermissions(this, getInitialPermissionsArray(), 0)
    }

    private fun getInitialPermissionsArray(): Array<String> {
        return arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
        )
    }
}
