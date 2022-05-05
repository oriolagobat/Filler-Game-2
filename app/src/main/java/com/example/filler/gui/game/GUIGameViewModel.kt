package com.example.filler.gui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GUIGameViewModel : ViewModel() {

    val setUpViewModel: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val mutableGameMediator: MutableLiveData<GameMediator> by lazy {
        MutableLiveData<GameMediator>()
    }
}