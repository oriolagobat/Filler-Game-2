package com.example.filler.gui.game.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filler.gui.game.GameMediator

class GUIGameViewModel : ViewModel() {
    val setUpViewModel: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val mutableGameMediator: MutableLiveData<GameMediator> by lazy {
        MutableLiveData<GameMediator>()
    }
}