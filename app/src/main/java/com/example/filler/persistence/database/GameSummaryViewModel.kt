package com.example.filler.persistence.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GameSummaryViewModel(private val repository: GameSummaryRepository) : ViewModel() {

    val summaries: MutableLiveData<List<GameSummary>> = MutableLiveData()

    companion object {
        var currentSummary: MutableLiveData<GameSummary?> = MutableLiveData(null)
    }

    init {
        initSummaries()
        if (currentSummary.value == null) {
            currentSummary.value = getFirstSummary()
        }
    }

    private fun initSummaries() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.getSummaries()
            }.join()
        }
        summaries.value = result
    }

    private fun getFirstSummary(): GameSummary? {
        return summaries.value?.firstOrNull()
    }

    fun insert(summary: GameSummary) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(summary)
    }

    fun delete(summary: GameSummary) {
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(summary)
            }.join()
        }
        summaries.value = summaries.value?.filter { it.id != summary.id }
    }

    fun updateCurrentSummary(summary: GameSummary) {
        currentSummary.value = summary
    }

    fun filterByAlias(alias: String) {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.getSummariesWithAlias(alias)
            }.join()
        }
        summaries.value = result
    }

    fun filterByAreaDesc() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.byAreaDesc()
            }.join()
        }
        summaries.value = result
    }

    fun filterByAreaAsc() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.byAreaAsc()
            }.join()
        }
        summaries.value = result
    }

    fun filterByVictory() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.getAllVictories()
            }.join()
        }
        summaries.value = result
    }

    fun filterByDefeat() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.getAllDefeats()
            }.join()
        }
        summaries.value = result
    }

    fun filterByDraw() {
        lateinit var result: List<GameSummary>
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                result = repository.getAllDraws()
            }.join()
        }
        summaries.value = result
    }
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
