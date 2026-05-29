package com.konuma.speaking.data

import com.konuma.speaking.model.Phrase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class Repository {
    // In-memory persistent state for this session
    private val phrasesState = MutableStateFlow(DataProvider.phrases)

    val allPhrases: Flow<List<Phrase>> = phrasesState

    val favoritePhrases: Flow<List<Phrase>> = phrasesState.map { phrases ->
        phrases.filter { it.isFavorite }
    }

    fun getPhrasesByCategory(categoryId: String): Flow<List<Phrase>> {
        return phrasesState.map { phrases ->
            phrases.filter { it.categoryId == categoryId }
        }
    }

    fun searchPhrases(query: String): Flow<List<Phrase>> {
        return phrasesState.map { phrases ->
            phrases.filter { 
                it.englishText.contains(query, ignoreCase = true) || 
                it.turkishText.contains(query, ignoreCase = true) 
            }
        }
    }

    suspend fun toggleFavorite(phraseId: String, isFavorite: Boolean) {
        val currentList = phrasesState.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == phraseId }
        if (index != -1) {
            currentList[index] = currentList[index].copy(isFavorite = isFavorite)
            phrasesState.value = currentList
        }
    }

    suspend fun initializeDatabase() {
        // No-op for in-memory
    }
}
