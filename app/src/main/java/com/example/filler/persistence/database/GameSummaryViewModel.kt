package com.example.filler.persistence.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filler.constants.gui.Summary
import com.example.filler.logic.game.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GameSummaryViewModel(private val repository: GameSummaryRepository) : ViewModel() {

    val summaries: MutableLiveData<List<GameSummary>> = MutableLiveData()
    val currentSummary: MutableLiveData<GameSummary?>

    init {
        updateSummaries()
        currentSummary = getFirstSummary()
    }

    private fun updateSummaries() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.getSummaries()
            }.join()
        }
        summaries.value = result
    }

    private fun getFirstSummary() = MutableLiveData(summaries.value?.get(0))

    fun insert(summary: GameSummary) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(summary)
    }

    fun delete(summary: GameSummary) {
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(summary)
            }.join()
        }
        updateSummaries()
    }

    fun updateCurrentSummary(summary: GameSummary) { currentSummary.value = summary }
}

class GameSummaryViewModelFactory(private val repository: GameSummaryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSummaryViewModel::class.java)) {
            return GameSummaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
