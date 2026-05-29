package com.konuma.speaking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konuma.speaking.data.DataProvider
import com.konuma.speaking.model.Phrase
import com.konuma.speaking.ui.viewmodel.PhraseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhraseListScreen(categoryId: String, onBackClick: () -> Unit) {
    val category = DataProvider.categories.find { it.id == categoryId }
    val phrases = DataProvider.getPhrasesForCategory(categoryId)
    
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredPhrases = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            phrases
        } else {
            phrases.filter { 
                it.englishText.contains(searchQuery, ignoreCase = true) ||
                it.turkishText.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(category?.name ?: "Phrases", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    ) { paddingValues ->
        if (filteredPhrases.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No phrases found", color = MaterialTheme.colorScheme.secondary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredPhrases) { phrase ->
                    PhraseCard(phrase)
                }
            }
        }
    }
}

@Composable
fun PhraseCard(
    phrase: Phrase,
    viewModel: PhraseViewModel = viewModel()
) {
    val isEmergency = phrase.categoryId == "emergency"
    val isLearningTurkish by viewModel.isLearningTurkish.collectAsState()
    
    val cardBg = if (isEmergency) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface
    val accentColor = if (isEmergency) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    
    // Expert Logic: Dynamic Role Swap
    val primaryText = if (isLearningTurkish) phrase.turkishText else phrase.englishText
    val secondaryText = if (isLearningTurkish) phrase.englishText else phrase.turkishText
    val speechTarget = if (isLearningTurkish) phrase.turkishText else phrase.englishText

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardBg,
            contentColor = if (isEmergency) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = accentColor.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = phrase.categoryId.replaceFirstChar { it.uppercase() },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                }
                if (isEmergency) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Secondary Reference (Muted)
            Text(
                text = secondaryText,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Primary Output (Focus)
            Text(
                text = primaryText,
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                color = accentColor,
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilledTonalIconButton(
                        onClick = { viewModel.speak(speechTarget, isTurkish = isLearningTurkish, isSlow = false) },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = accentColor.copy(alpha = 0.1f)
                        )
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Listen", tint = accentColor)
                    }
                    
                    IconButton(onClick = { viewModel.speak(speechTarget, isTurkish = isLearningTurkish, isSlow = true) }) {
                        Text("🐢", fontSize = 20.sp)
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(onClick = { viewModel.toggleFavorite(phrase) }) {
                        Icon(
                            imageVector = if (phrase.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (phrase.isFavorite) Color(0xFFD32F2F) else MaterialTheme.colorScheme.outline
                        )
                    }
                    IconButton(onClick = { /* TODO: Share */ }) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}
