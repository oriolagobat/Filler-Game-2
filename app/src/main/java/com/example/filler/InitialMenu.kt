package com.example.filler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.filler.databinding.ActivityMainBinding

class InitialMenu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare the binding of the view
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set listeners to the buttons
        binding.helpButton.setOnClickListener(this)
        binding.newGameButton.setOnClickListener(this)
        binding.quitButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        lateinit var intent: Intent

        when (v?.id) {
            R.id.helpButton -> {intent = Intent(this, Help::class.java)}
            R.id.newGameButton -> {
                TODO("To be implemented")
//                intent = Intent(this, TODO(NewGame::class.java))
            }
            R.id.quitButton -> finish()
            // FIXME: Put everything in one line once implemented
        }

        startActivity(intent)
    }
}