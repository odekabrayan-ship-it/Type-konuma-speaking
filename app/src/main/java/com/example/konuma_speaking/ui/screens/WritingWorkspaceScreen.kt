package com.example.konuma_speaking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.konuma_speaking.data.WritingDataProvider
import com.example.konuma_speaking.model.BilingualSentence

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun WritingWorkspaceScreen(
    topicId: String,
    onBackClick: () -> Unit
) {
    val topic = WritingDataProvider.topics.find { it.id == topicId } ?: return
    var userText by remember { mutableStateOf("") }
    var activeTab by remember { mutableStateOf(0) } // 0: Write, 1: Sentence Model

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yazma Workspace", fontWeight = FontWeight.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Level and Grammar Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Level: ${topic.level}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(topic.grammarFocus, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
            }

            // Tab Selector
            TabRow(selectedTabIndex = activeTab) {
                Tab(selected = activeTab == 0, onClick = { activeTab = 0 }) {
                    Text("📝 Write", modifier = Modifier.padding(12.dp))
                }
                Tab(selected = activeTab == 1, onClick = { activeTab = 1 }) {
                    Text("📘 Sentence Model", modifier = Modifier.padding(12.dp))
                }
            }

            if (activeTab == 0) {
                // WRITE MODE
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Task
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(topic.titleTr, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(topic.task, fontSize = 14.sp)
                        }
                    }

                    // Useful Words
                    Text("💡 Useful Words", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    androidx.compose.foundation.layout.FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        topic.usefulWords.forEach { word ->
                            SuggestionChip(
                                onClick = { userText += " $word " },
                                label = { Text(word) }
                            )
                        }
                    }

                    // Editor
                    OutlinedTextField(
                        value = userText,
                        onValueChange = { userText = it },
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                        placeholder = { Text("Start writing your exam answer...") },
                        shape = RoundedCornerShape(16.dp)
                    )

                    // Word Count
                    val wordCount = if (userText.isBlank()) 0 else userText.trim().split("\\s+".toRegex()).size
                    Text(
                        "Word count: $wordCount / ${topic.wordCountGuide}",
                        modifier = Modifier.align(Alignment.End),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                // SENTENCE MODEL MODE (Study side-by-side mapping)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            "Study the mapping between English and Turkish to build correct exam structures.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(topic.sentenceModel) { sentence ->
                        SentenceMappingCard(sentence)
                    }
                }
            }
        }
    }
}

@Composable
fun SentenceMappingCard(sentence: BilingualSentence) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(sentence.english, fontSize = 13.sp, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(sentence.turkish, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}
