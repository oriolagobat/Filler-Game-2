package com.example.filler.gui.configuration

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.databinding.ActivityNewGameConfigurationBinding
import com.example.filler.gui.shared.hideNavBar

class NewGameConfiguration : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityNewGameConfigurationBinding
    private lateinit var gameConf: GameConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavBar(this)
        binding = ActivityNewGameConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize game configuration
        gameConf = GameConfiguration()

        // Start the song player
        startConfSong(this)

        // Set this class listeners
        setUpConfigListeners(this, binding)
    }

    override fun onPause() {
        super.onPause()
        stopConfSong(this)
    }

    //  Manages spinner selections
    override fun onItemSelected(spinner: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gameConf.spinnerClick(spinner, position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) =
        throw UnsupportedOperationException("Cannot be thrown, options won't be removed from the list")

    override fun onClick(v: View?) {
        gameConf.click(v, this, binding)
    }

    // Manages clicks on the radioGroup
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        gameConf.radioButtonClick(this, checkedId)
    }

    override fun onBackPressed() {
        return
    }
}