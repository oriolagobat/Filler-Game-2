package com.example.filler.persistence.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.*

class GameSummaryViewModel(private val repository: GameSummaryRepository) : ViewModel() {

    val allGameSummaries: LiveData<List<GameSummary>> = repository.allSummaries.asLiveData()

    fun insert(summary: GameSummary) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(summary)
    }
}

class GameSummaryViewModelFactory(private val repository: GameSummaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSummaryViewModel::class.java)) {
            return GameSummaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
