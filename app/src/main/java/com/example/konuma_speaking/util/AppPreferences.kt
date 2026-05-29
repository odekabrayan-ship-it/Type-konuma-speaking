package com.example.konuma_speaking.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_settings")

class AppPreferences(private val context: Context) {
    companion object {
        private val LEARNING_TURKISH = booleanPreferencesKey("learning_turkish")
    }

    val isLearningTurkish: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[LEARNING_TURKISH] ?: true // Default is learning Turkish
    }

    suspend fun toggleLearningMode() {
        context.dataStore.edit { prefs ->
            val current = prefs[LEARNING_TURKISH] ?: true
            prefs[LEARNING_TURKISH] = !current
        }
    }
}
