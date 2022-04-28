package com.example.filler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.filler.databinding.ActivityChooseResultsBinding
import com.example.filler.gui.Results
import com.example.filler.gui.configuration.NewGameConfiguration
import com.example.filler.gui.game.Game

class ChooseResults : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChooseResultsBinding =
            ActivityChooseResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stubResultWin.setOnClickListener(this)
        binding.stubResultLoose.setOnClickListener(this)
        binding.stubResultDraw.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, Results::class.java)
        val stringId = "resultType"
        when (v?.id) {
            R.id.stubResultWin -> intent.putExtra(stringId, "win")
            R.id.stubResultLoose -> intent.putExtra(stringId, "loose")
            R.id.stubResultDraw -> intent.putExtra(stringId, "draw")
        }
        startActivity(intent)
    }
}