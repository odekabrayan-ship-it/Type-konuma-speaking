package com.example.konuma_speaking.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.konuma_speaking.data.DataProvider
import com.example.konuma_speaking.model.Phrase
import com.example.konuma_speaking.ui.screens.PhraseCard

@Composable
fun EmergencyOverlay(
    isVisible: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val emergencyPhrases = DataProvider.phrases.filter { it.categoryId == "emergency" }.take(5)
    
    // Turkish Emergency Contacts
    val contacts = listOf(
        EmergencyContact("112", "All Emergencies (Ambulance/Police/Fire)", "112"),
        EmergencyContact("155", "Police (Polis İmdat)", "155"),
        EmergencyContact("110", "Fire (İtfaiye)", "110")
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .clickable { onDismiss() }
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .clickable(enabled = false) { },
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB71C1C)) // Deep SOS Red
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "QUICK SOS",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Emergency Contacts Section
                    Text("Direct Dial:", color = Color.White.copy(alpha = 0.8f), fontWeight = FontWeight.Bold)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        contacts.forEach { contact ->
                            EmergencyContactButton(contact) {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.number}"))
                                context.startActivity(intent)
                            }
                        }
                    }

                    Divider(color = Color.White.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 16.dp))

                    Text("Critical Phrases:", color = Color.White.copy(alpha = 0.8f), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(emergencyPhrases) { phrase ->
                            SOSPhraseCard(phrase)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmergencyContactButton(contact: EmergencyContact, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier.height(60.dp),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(contact.number, color = Color(0xFFB71C1C), fontWeight = FontWeight.Black, fontSize = 20.sp)
            Text(contact.label.take(10) + "...", color = Color.Gray, fontSize = 10.sp)
        }
    }
}

@Composable
fun SOSPhraseCard(phrase: Phrase) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(phrase.englishText, fontSize = 14.sp, color = Color.Gray)
            Text(phrase.turkishText, fontSize = 22.sp, fontWeight = FontWeight.Black, color = Color(0xFFB71C1C))
        }
    }
}

data class EmergencyContact(val number: String, val label: String, val dialUri: String)
