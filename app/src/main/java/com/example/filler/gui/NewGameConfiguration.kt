package com.example.filler.gui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.filler.R
import com.example.filler.databinding.ActivityNewGameConfigurationBinding

class NewGameConfiguration : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityNewGameConfigurationBinding

    private lateinit var username: String
    private lateinit var colorNum: String
    private lateinit var gridNum: String
    private var timeControl = false
    private lateinit var difficulty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set this class binding
        binding = ActivityNewGameConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the onClickListener for the editText, checkBox and button
        binding.usernameInput.setOnClickListener(this)
        binding.timeCheckBox.setOnClickListener(this)
        binding.newGameButton.setOnClickListener(this)

        // Set the spinners onItemSelectedListener's
        binding.colorSpinner.onItemSelectedListener = this
        binding.gridSpinner.onItemSelectedListener = this

        // Set radioGroup's onCheckedChangeListener
        binding.difficultyRadioGroup.setOnCheckedChangeListener(this)
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

    private fun manageUsernameInput() {
        if (binding.usernameInput.text.isEmpty()) {
            binding.usernameInput.error = "Please enter a username"
        } else {
            username = binding.usernameInput.text.toString()
        }
        closeKeyboardClearFocus()
    }

    private fun closeKeyboardClearFocus() {
        val focused: View? = currentFocus
        focused?.let {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(focused.windowToken, 0)
        }
        // Clear the EditText focus
        binding.usernameInput.clearFocus()
    }

    private fun manageTimeCheckBox() {
        timeControl = binding.timeCheckBox.isChecked
    }

    private fun manageNewGameButton() {
        if (!::username.isInitialized || username.isEmpty()) {
            val errorMsg = "Please enter a username"
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        } else if (!::difficulty.isInitialized) {
            val errorMsg = "Please select a difficulty"
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, Game::class.java)
            intent.putExtra("username", username)
            intent.putExtra("colorNum", colorNum)
            intent.putExtra("gridNum", gridNum)
            intent.putExtra("timeControl", timeControl)
            intent.putExtra("difficulty", difficulty)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }


    // Manages clicks on the radioGroup
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val checked: RadioButton = findViewById(checkedId)
        difficulty = checked.text.toString()
    }

}