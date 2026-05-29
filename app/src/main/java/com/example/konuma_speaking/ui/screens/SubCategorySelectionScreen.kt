package com.example.konuma_speaking.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.konuma_speaking.data.DataProvider
import com.example.konuma_speaking.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCategorySelectionScreen(
    categoryId: String,
    onSubCategoryClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val category = DataProvider.categories.find { it.id == categoryId }
    val subCategories = DataProvider.getSubCategoriesForCategory(categoryId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category?.name ?: "Select Section", fontWeight = FontWeight.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "What do you need specifically?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            // "Show All" option for broad learning
            item {
                SubCategoryCard(name = "Show All Phrases", icon = "🌟") {
                    onSubCategoryClick("All")
                }
            }

            items(subCategories) { subCat ->
                SubCategoryCard(name = subCat, icon = "📍") {
                    onSubCategoryClick(subCat)
                }
            }
        }
    }
}

@Composable
fun SubCategoryCard(name: String, icon: String, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}
