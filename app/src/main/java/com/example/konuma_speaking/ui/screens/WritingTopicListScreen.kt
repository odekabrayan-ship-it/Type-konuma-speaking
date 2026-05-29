package com.example.konuma_speaking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.konuma_speaking.data.WritingDataProvider
import com.example.konuma_speaking.model.WritingDomain
import com.example.konuma_speaking.model.WritingTopic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingTopicListScreen(
    level: String,
    domain: WritingDomain,
    onTopicClick: (WritingTopic) -> Unit,
    onBackClick: () -> Unit
) {
    val filteredTopics = remember(level, domain) {
        WritingDataProvider.topics.filter { it.level == level && it.domain == domain }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(domain.label, fontWeight = FontWeight.Black)
                        Text("Level $level • ${filteredTopics.size} Essays", fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (filteredTopics.isEmpty()) {
                EmptyLevelState(level) // Reusing empty state
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredTopics) { topic ->
                        WritingTopicCard(topic = topic, onClick = { onTopicClick(topic) })
                    }
                }
            }
        }
    }
}
