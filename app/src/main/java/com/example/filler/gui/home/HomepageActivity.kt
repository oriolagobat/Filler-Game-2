package com.example.filler.gui.home

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.filler.R
import com.example.filler.databinding.ActivityMainBinding
import com.example.filler.gui.game.GameActivity
import com.example.filler.gui.help.HelpActivity
import com.example.filler.gui.preferences.PreferencesActivity
//import com.example.filler.gui.preferences.PreferencesActivity
import com.example.filler.gui.shared.hideNavBar
import com.example.filler.persistence.AccessBDActivity
import com.google.android.material.color.DynamicColors

class HomepageActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Request permissions
        checkAndRequestInitialPermissions()

        // Set the dynamic colors
        DynamicColors.applyToActivitiesIfAvailable(application)

        // Splash screen
        installSplashScreen()

        // Get the binding and set the view
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set listeners to the buttons
        binding.helpButton.setOnClickListener(this)
        binding.newGameButton.setOnClickListener(this)
        binding.queryButton.setOnClickListener(this)
        binding.quitButton.setOnClickListener(this)
        binding.settingsButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.helpButton -> startHelpScreen()
            R.id.newGameButton -> startGameScreen()
            R.id.queryButton -> startQueryScreen()
            R.id.settingsButton -> startSettingsScreen()
            R.id.quitButton -> finish()
            else -> throw IllegalArgumentException("No more buttons")
        }
    }

    private fun startHelpScreen() {
        val helpIntent = Intent(this, HelpActivity::class.java)
        startActivity(helpIntent)
    }

    private fun startGameScreen() {
        val gameIntent = Intent(this, GameActivity::class.java)
        startActivity(gameIntent)
        finish()
    }

    private fun startQueryScreen() {
        val queryIntent = Intent(this, AccessBDActivity::class.java)
        startActivity(queryIntent)
    }

    private fun startSettingsScreen() {
        val settingsIntent = Intent(this, PreferencesActivity::class.java)
        startActivity(settingsIntent)
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

    override fun onResume() {
        super.onResume()
        hideNavBar(this)
    }
}