package com.example.konuma_speaking.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konuma_speaking.data.DataProvider
import com.example.konuma_speaking.data.SearchEngine
import com.example.konuma_speaking.model.Category
import com.example.konuma_speaking.model.Phrase
import com.example.konuma_speaking.ui.components.EmergencyOverlay
import com.example.konuma_speaking.ui.components.ProgressDashboard
import com.example.konuma_speaking.ui.viewmodel.PhraseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    onCategoryClick: (Category) -> Unit,
    onGlobalSearchClick: (Phrase) -> Unit,
    onWritingClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: PhraseViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSOSVisible by remember { mutableStateOf(false) }
    val isLearningTurkish by viewModel.isLearningTurkish.collectAsState()
    
    val masteredCount = 45 
    val totalCount = 600
    val currentStreak = 5

    val filteredCategories = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            DataProvider.categories
        } else {
            DataProvider.categories.filter { 
                it.name.contains(searchQuery, ignoreCase = true) 
            }
        }
    }

    val globalSearchResults = remember(searchQuery) {
        SearchEngine.searchAllPhrases(searchQuery)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            floatingActionButton = {
                Column(horizontalAlignment = Alignment.End) {
                    // Favorites FAB
                    SmallFloatingActionButton(
                        onClick = onFavoritesClick,
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.tertiary,
                        shape = androidx.compose.foundation.shape.CircleShape
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    SmallFloatingActionButton(
                        onClick = onWritingClick,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        shape = androidx.compose.foundation.shape.CircleShape
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Writing")
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    FloatingActionButton(
                        onClick = { isSOSVisible = true },
                        containerColor = Color(0xFFB71C1C),
                        contentColor = Color.White,
                        shape = androidx.compose.foundation.shape.CircleShape,
                        modifier = Modifier.size(72.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Warning, contentDescription = "SOS")
                            Text("SOS", fontSize = 12.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            },
            topBar = {
                Column {
                    CenterAlignedTopAppBar(
                        title = { Text("Survival Turkish", fontWeight = FontWeight.ExtraBold) },
                        actions = {
                            TextButton(onClick = { viewModel.toggleLearningMode() }) {
                                Text(
                                    if (isLearningTurkish) "TR 🇹🇷" else "EN 🇺🇸",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
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
                    
                    AnimatedVisibility(
                        visible = searchQuery.isEmpty(),
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        ProgressDashboard(
                            masteredCount = masteredCount,
                            totalCount = totalCount,
                            streak = currentStreak
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                if (searchQuery.isNotEmpty() && globalSearchResults.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        item {
                            Text(
                                "Phrases found:",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        items(globalSearchResults.take(15)) { phrase ->
                            PhraseCard(phrase = phrase, viewModel = viewModel)
                        }
                    }
                } else if (filteredCategories.isEmpty() && globalSearchResults.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No results found offline", color = MaterialTheme.colorScheme.secondary)
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(filteredCategories) { index, category ->
                            val state = remember { MutableTransitionState(false) }.apply { targetState = true }
                            AnimatedVisibility(
                                visibleState = state,
                                enter = slideInVertically(
                                    initialOffsetY = { 100 * (index + 1) },
                                    animationSpec = tween(600, delayMillis = index * 50, easing = FastOutSlowInEasing)
                                ) + fadeIn(animationSpec = tween(600, delayMillis = index * 50))
                            ) {
                                CategoryItem(category = category, onClick = { onCategoryClick(category) })
                            }
                        }
                    }
                }
            }
        }

        EmergencyOverlay(
            isVisible = isSOSVisible,
            onDismiss = { isSOSVisible = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = { Text("Search situations (e.g. Hospital)") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    val isEmergency = category.id == "emergency"
    val containerColor = if (isEmergency) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface
    val contentColor = if (isEmergency) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSurface
    val iconBgColor = if (isEmergency) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primaryContainer

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() },
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = androidx.compose.foundation.shape.CircleShape,
                color = iconBgColor,
                modifier = Modifier.size(64.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = category.icon, fontSize = 34.sp)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = category.name, 
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = contentColor,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 18.sp
            )
        }
    }
}
