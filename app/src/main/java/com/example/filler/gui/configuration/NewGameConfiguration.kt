package com.example.filler.gui.configuration

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding
import com.example.filler.gui.getValidUsernameOrError
import com.example.filler.gui.hideNavBar

class NewGameConfiguration : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityNewGameConfigurationBinding

    private var username: String? = null
    private var difficulty: String? = null
    private lateinit var colorNum: String
    private lateinit var gridNum: String
    private var timeControl = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the navbar
        hideNavBar(this)

        // Set this class binding
        binding = ActivityNewGameConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        // Don't ignore first call, it will serve as the default if user doesn't select anything
        when (spinner?.id) {
            R.id.colorSpinner -> colorNum = getItemFromSpinner(spinner, position)
            R.id.gridSpinner -> gridNum = getItemFromSpinner(spinner, position)
        }
    }

    private fun getItemFromSpinner(spinner: AdapterView<*>, position: Int): String =
        spinner.getItemAtPosition(position).toString()

    override fun onNothingSelected(parent: AdapterView<*>?) {
        val errorMsg = "Cannot be thrown, options won't be removed from the list"
        throw UnsupportedOperationException(errorMsg)
    }


    // Manages clicks on the editText, checkBox and button
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.usernameInput -> manageUsernameInput()
            R.id.timeCheckBox -> manageTimeCheckBox()
            R.id.startNewGameButton -> manageNewGameButton()
        }
    }

    // Manages the username input EditText
    private fun manageUsernameInput() {
        username = getValidUsernameOrError(this, binding.usernameInput)
    }

    // Manages time checkBox
    private fun manageTimeCheckBox() {
        timeControl = binding.timeCheckBox.isChecked
    }

    // Manages new game button
    private fun manageNewGameButton() {
        if (correctGameSettings(this, difficulty, username)) {
            StartNewGame(this, username!!, colorNum, gridNum, timeControl, difficulty!!)
        }
    }

    // Manages clicks on the radioGroup
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val checked: RadioButton = findViewById(checkedId)
        difficulty = checked.text.toString()
    }
}
