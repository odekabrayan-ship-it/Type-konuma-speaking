package com.example.konuma_speaking.model

data class WritingTopic(
    val id: String,
    val level: String, // A1, A2, B1, B2, C1
    val domain: WritingDomain, // Category: Personal, Society, Environment, etc.
    val titleTr: String,
    val titleEn: String,
    val task: String,
    val usefulWords: List<String>,
    val sentenceModel: List<BilingualSentence>,
    val grammarFocus: String,
    val wordCountGuide: String
)

enum class WritingDomain(val label: String, val icon: String) {
    PERSONAL("Personal Life", "👤"),
    DAILY_LIFE("Daily Routine", "🏠"),
    SOCIAL("Social Life", "🤝"),
    ACADEMIC("University Life", "🎓"),
    TECHNOLOGY("Technology", "💻"),
    TRANSPORT("Transportation", "🚌"),
    HEALTH("Health", "🏥"),
    SOCIETY("Society & Culture", "🌍"),
    CAREER("Work & Career", "💼"),
    ENVIRONMENT("Environment", "🌱")
}

data class BilingualSentence(
    val english: String,
    val turkish: String
)
