package com.konuma.speaking.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.konuma.speaking.data.DataProvider
import com.konuma.speaking.model.Phrase
import com.konuma.speaking.ui.viewmodel.PhraseViewModel
import kotlin.math.absoluteValue

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SentenceFeedScreen(
    categoryId: String,
    initialSubCategory: String = "All",
    onBackClick: () -> Unit,
    viewModel: PhraseViewModel = viewModel()
) {
    val category = DataProvider.categories.find { it.id == categoryId }
    val allPhrases = DataProvider.getPhrasesForCategory(categoryId)
    val subCategories = remember(categoryId) { 
        listOf("All") + DataProvider.getSubCategoriesForCategory(categoryId) 
    }
    
    var selectedSubCategory by remember { mutableStateOf(initialSubCategory) }
    val isLearningTurkish by viewModel.isLearningTurkish.collectAsState()
    
    val filteredPhrases = remember(selectedSubCategory) {
        if (selectedSubCategory == "All") allPhrases 
        else allPhrases.filter { it.subCategory == selectedSubCategory }
    }
    
    val pagerState = rememberPagerState(pageCount = { filteredPhrases.size })
    
    LaunchedEffect(selectedSubCategory) {
        pagerState.scrollToPage(0)
    }
    
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { 
                        Column {
                            Text(category?.name ?: "Learn", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text("${pagerState.currentPage + 1} / ${filteredPhrases.size}", fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
                
                ScrollableTabRow(
                    selectedTabIndex = subCategories.indexOf(selectedSubCategory),
                    edgePadding = 16.dp,
                    containerColor = MaterialTheme.colorScheme.surface,
                    divider = {},
                    indicator = { } 
                ) {
                    subCategories.forEach { subCat ->
                        FilterChip(
                            selected = selectedSubCategory == subCat,
                            onClick = { selectedSubCategory = subCat },
                            label = { Text(subCat) },
                            modifier = Modifier.padding(horizontal = 4.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .draggable(
                    state = rememberDraggableState { delta ->
                        if (delta > 80) onBackClick() 
                    },
                    orientation = Orientation.Vertical
                )
        ) {
            if (filteredPhrases.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No phrases in this section")
                }
            } else {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 40.dp),
                    pageSpacing = 16.dp
                ) { page ->
                    val pageOffset = (
                        (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue

                    Box(
                        modifier = Modifier
                            .graphicsLayer {
                                val scale = lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                scaleX = scale
                                scaleY = scale
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                    ) {
                        FullScreenPhraseCard(
                            phrase = filteredPhrases[page],
                            isLearningTurkish = isLearningTurkish,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FullScreenPhraseCard(
    phrase: Phrase,
    isLearningTurkish: Boolean,
    viewModel: PhraseViewModel
) {
    val isEmergency = phrase.categoryId == "emergency"
    val cardBg = if (isEmergency) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface
    val accentColor = if (isEmergency) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    
    // Expert Logic: Dynamic Role Swap
    val primaryText = if (isLearningTurkish) phrase.turkishText else phrase.englishText
    val secondaryText = if (isLearningTurkish) phrase.englishText else phrase.turkishText
    val speechTarget = if (isLearningTurkish) phrase.turkishText else phrase.englishText

    Card(
        modifier = Modifier.fillMaxSize().padding(vertical = 48.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Secondary Reference
            Text(
                text = secondaryText,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Primary Output
            Text(
                text = primaryText,
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = accentColor,
                lineHeight = 44.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilledTonalIconButton(
                    onClick = { viewModel.speak(speechTarget, isTurkish = isLearningTurkish, isSlow = false) },
                    modifier = Modifier.size(72.dp),
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = accentColor.copy(alpha = 0.1f)
                    )
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Play", modifier = Modifier.size(40.dp), tint = accentColor)
                }
                
                IconButton(onClick = { viewModel.speak(speechTarget, isTurkish = isLearningTurkish, isSlow = true) }, modifier = Modifier.size(72.dp)) {
                    Text("🐢", fontSize = 32.sp)
                }
                
                IconButton(onClick = { viewModel.toggleFavorite(phrase) }, modifier = Modifier.size(72.dp)) {
                    Icon(
                        imageVector = if (phrase.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Save",
                        modifier = Modifier.size(36.dp),
                        tint = if (phrase.isFavorite) Color(0xFFD32F2F) else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
