package com.example.filler.gui.results.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultsViewModel : ViewModel() {
    val firstResultCreation: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }
}