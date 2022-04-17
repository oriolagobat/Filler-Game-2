package com.example.filler.gui.configuration

import com.example.filler.databinding.ActivityNewGameConfigurationBinding

class ConfigurationListenersSetUp(
    context: NewGameConfiguration,
    binding: ActivityNewGameConfigurationBinding
) {
    init {
        // Set the onClickListener for the editText, checkBox and button
        binding.usernameInput.setOnClickListener(context)
        binding.timeCheckBox.setOnClickListener(context)
        binding.newGameButton.setOnClickListener(context)

        // Set the spinners onItemSelectedListener's
        binding.colorSpinner.onItemSelectedListener = context
        binding.gridSpinner.onItemSelectedListener = context

        // Set radioGroup's onCheckedChangeListener
        binding.difficultyRadioGroup.setOnCheckedChangeListener(context)
    }
}
