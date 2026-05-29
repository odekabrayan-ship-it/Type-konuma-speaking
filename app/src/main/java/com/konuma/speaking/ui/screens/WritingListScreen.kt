package com.konuma.speaking.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konuma.speaking.data.WritingDataProvider
import com.konuma.speaking.model.WritingTopic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingListScreen(
    onTopicClick: (WritingTopic) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedLevel by remember { mutableStateOf("A1") }
    val levels = listOf("A1", "A2", "B1", "B2", "C1")
    
    val filteredTopics = remember(selectedLevel) {
        WritingDataProvider.topics.filter { it.level == selectedLevel }
    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                TopAppBar(
                    title = { Text("Yazma Library", fontWeight = FontWeight.Black) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
                
                // 📌 HEADER SECTION
                WritingSectionHeader()
                
                // 🧭 LEVEL SELECTOR (PRIMARY NAVIGATION)
                LevelSelector(
                    levels = levels,
                    selectedLevel = selectedLevel,
                    onLevelSelected = { selectedLevel = it }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (filteredTopics.isEmpty()) {
                EmptyLevelState(selectedLevel)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(filteredTopics) { index, topic ->
                        val state = remember { MutableTransitionState(false) }.apply { targetState = true }
                        AnimatedVisibility(
                            visibleState = state,
                            enter = slideInHorizontally(
                                initialOffsetX = { 200 },
                                animationSpec = tween(500, delayMillis = index * 50, easing = FastOutSlowInEasing)
                            ) + fadeIn(animationSpec = tween(500, delayMillis = index * 50))
                        ) {
                            WritingTopicCard(topic = topic, onClick = { onTopicClick(topic) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WritingSectionHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Model Essay Library",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "A1 → C1 Exam Writing Preparation",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Read • Learn • Memorize • Reproduce",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun LevelSelector(
    levels: List<String>,
    selectedLevel: String,
    onLevelSelected: (String) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = levels.indexOf(selectedLevel),
        edgePadding = 16.dp,
        containerColor = Color.Transparent,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[levels.indexOf(selectedLevel)]),
                height = 3.dp,
                color = getLevelColor(selectedLevel)
            )
        }
    ) {
        levels.forEach { level ->
            Tab(
                selected = selectedLevel == level,
                onClick = { onLevelSelected(level) },
                text = {
                    Text(
                        text = level,
                        fontWeight = if (selectedLevel == level) FontWeight.Black else FontWeight.Bold,
                        fontSize = 14.sp
                    )
                },
                selectedContentColor = getLevelColor(level),
                unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun WritingTopicCard(topic: WritingTopic, onClick: () -> Unit) {
    val levelColor = getLevelColor(topic.level)

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Level Indicator Dot
            Surface(
                modifier = Modifier.size(12.dp),
                shape = androidx.compose.foundation.shape.CircleShape,
                color = levelColor
            ) {}
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = topic.titleTr,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = topic.grammarFocus,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun EmptyLevelState(level: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("📚", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Coming Soon for $level",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                "We are expanding our library.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
            )
        }
    }
}

fun getLevelColor(level: String): Color {
    return when (level) {
        "A1" -> Color(0xFF4CAF50) // Green
        "A2" -> Color(0xFFFFEB3B) // Yellow
        "B1" -> Color(0xFFFF9800) // Orange
        "B2" -> Color(0xFF2196F3) // Blue
        "C1" -> Color(0xFF9C27B0) // Purple
        else -> Color.Gray
    }
}
