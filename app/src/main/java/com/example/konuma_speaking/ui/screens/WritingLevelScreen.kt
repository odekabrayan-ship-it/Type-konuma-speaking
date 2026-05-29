package com.example.konuma_speaking.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingLevelScreen(
    onLevelSelected: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val levels = listOf(
        WritingLevelItem("A1", "Beginner", "Foundations of Turkish", Color(0xFF4CAF50)),
        WritingLevelItem("A2", "Elementary", "Basic daily interactions", Color(0xFFFFEB3B)),
        WritingLevelItem("B1", "Intermediate", "Complex social topics", Color(0xFFFF9800)),
        WritingLevelItem("B2", "Upper Intermediate", "Professional discussions", Color(0xFF2196F3)),
        WritingLevelItem("C1", "Advanced", "Academic & abstract ideas", Color(0xFF9C27B0))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose Your Level", fontWeight = FontWeight.Black) },
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
            WritingSectionHeader() // Reusing the header for consistency
            
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(levels) { level ->
                    LevelCard(level = level, onClick = { onLevelSelected(level.code) })
                }
            }
        }
    }
}

@Composable
fun LevelCard(level: WritingLevelItem, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                color = level.color.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(level.code, fontWeight = FontWeight.Black, fontSize = 20.sp, color = level.color)
                }
            }
            
            Spacer(modifier = Modifier.width(20.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(level.label, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Text(level.description, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
            }
            
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = level.color)
        }
    }
}

data class WritingLevelItem(val code: String, val label: String, val description: String, val color: Color)
