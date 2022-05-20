package com.example.filler.gui.home

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.filler.R
import com.example.filler.databinding.ActivityMainBinding
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
        // FIXME: Implement help class and uncomment
//        val helpIntent = Intent(this, Help::class.java)
//        startActivity(helpIntent)
    }

    private fun startGameScreen() {
        // FIXME: Implement game class and uncomment
//        val gameIntent = Intent(this, NewGame::class.java)
//        startActivity(gameIntent)
        finish()
    }

    private fun startQueryScreen() {
        // FIXME: Implement query class and uncomment
//        val queryIntent = Intent(this, Query::class.java)
//        startActivity(queryIntent)
    }

    private fun startSettingsScreen() {
        // FIXME: Implement settings class and uncomment
//        val settingsIntent = Intent(this, Settings::class.java)
//        startActivity(settingsIntent)
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