package com.example.konuma_speaking.model

data class Phrase(
    val id: String,
    val categoryId: String,
    val subCategory: String, // e.g., "Bus", "Taxi", "Classroom"
    val englishText: String,
    val turkishText: String,
    val isFavorite: Boolean = false
)
