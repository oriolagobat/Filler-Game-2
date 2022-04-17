package com.example.filler.gui.configuration

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding

class NewGameConfiguration : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityNewGameConfigurationBinding

    private lateinit var username: String
    private lateinit var usernameInput: NewUsernameInput
    private lateinit var colorNum: String
    private lateinit var gridNum: String
    private var timeControl = false
    private lateinit var difficulty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set this class binding
        binding = ActivityNewGameConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set this class listeners
        ConfigurationListenersSetUp(this, binding)

        // Set a new username input instance
        usernameInput = NewUsernameInput(this, binding)
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
            R.id.newGameButton -> manageNewGameButton()
        }
    }

    // Manages the username input EditText
    private fun manageUsernameInput() {
        username = usernameInput.get()
    }


    // Manages time checkBox
    private fun manageTimeCheckBox() {
        timeControl = binding.timeCheckBox.isChecked
    }

    // Manages new game button
    private fun manageNewGameButton() {
        val errorMsg: String? = when {
            // Check possible variables that don't have a default value
            (!::username.isInitialized) -> "Enter a username"
            (!::difficulty.isInitialized) -> "Select a difficulty"
            else -> null
        }

        if (errorMsg != null) {
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        } else {
            StartNewGame(this, username, colorNum, gridNum, timeControl, difficulty)
        }
    }


    // Manages clicks on the radioGroup
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val checked: RadioButton = findViewById(checkedId)
        difficulty = checked.text.toString()
    }

}