package com.konuma.speaking.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.konuma.speaking.data.Repository
import com.konuma.speaking.model.Phrase
import com.konuma.speaking.util.AppPreferences
import com.konuma.speaking.util.TtsManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PhraseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val ttsManager: TtsManager
    private val prefs = AppPreferences(application)

    val isLearningTurkish: StateFlow<Boolean> = prefs.isLearningTurkish.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = true
    )
    
    val favoritePhrases: Flow<List<Phrase>> = repository.favoritePhrases

    init {
        ttsManager = TtsManager(application)
    }

    fun toggleLearningMode() {
        viewModelScope.launch {
            prefs.toggleLearningMode()
        }
    }

    fun speak(text: String, isTurkish: Boolean, isSlow: Boolean = false) {
        ttsManager.speak(text, isTurkish, isSlow)
    }

    fun toggleFavorite(phrase: Phrase) {
        viewModelScope.launch {
            repository.toggleFavorite(phrase.id, !phrase.isFavorite)
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}
