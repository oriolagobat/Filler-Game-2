package com.example.filler.gui.configuration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filler.gui.configuration.GameConfiguration

class ConfigurationGameViewModel : ViewModel() {
    val setUpViewModel: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val mutableGameConfiguration: MutableLiveData<GameConfiguration> by lazy {
        MutableLiveData<GameConfiguration>()
    }
}