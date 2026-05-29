package com.example.konuma_speaking.data

import com.example.konuma_speaking.model.Phrase

/**
 * Professional Offline Search Engine
 * Optimized for real-time situational retrieval.
 */
object SearchEngine {

    /**
     * Performs a deep, multi-language search across the entire offline database.
     * Prioritizes relevance by checking categories first, then exact matches.
     */
    fun searchAllPhrases(query: String): List<Phrase> {
        if (query.isBlank()) return emptyList()

        val normalizedQuery = query.trim().lowercase()
        
        return DataProvider.phrases.filter { phrase ->
            // Match against English text
            phrase.englishText.lowercase().contains(normalizedQuery) ||
            // Match against Turkish text
            phrase.turkishText.lowercase().contains(normalizedQuery) ||
            // Match against Category name
            phrase.categoryId.lowercase().contains(normalizedQuery) ||
            // Match against SubCategory name
            phrase.subCategory.lowercase().contains(normalizedQuery)
        }.sortedByDescending { phrase ->
            // Scoring for relevance
            when {
                phrase.englishText.lowercase().startsWith(normalizedQuery) -> 3
                phrase.turkishText.lowercase().startsWith(normalizedQuery) -> 3
                phrase.categoryId.lowercase() == normalizedQuery -> 2
                else -> 1
            }
        }
    }
}
