package com.example.kaynewest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaynewest.model.Quotes
import com.example.kaynewest.util.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MyApp(quote: MutableState<Quotes>){
    val coroutineScope = rememberCoroutineScope()
    fun fetchQuote() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getQuote()
                // Update the quote state on the main thread
                withContext(Dispatchers.Main) {
                    quote.value = response
                }
            } catch (e: Exception) {
                return@launch
            }
        }
    }
    LaunchedEffect(Unit) {
        fetchQuote()
    }
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF1A003E), Color(0xFF000000)), // Midnight purple to black
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
            .padding(top = 250.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.size(160.dp),
            contentDescription = null,
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xAA121212)
            ),
            modifier = Modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Text(
                text = quote.value.quote,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                color = Color(0xFFFFFFFF)
            )
        }
        Icon(imageVector = Icons.Default.Refresh,
            contentDescription = null,
            tint = Color(0xFFFFE082),
            modifier = Modifier
                .clickable {
                    fetchQuote()
                }
        )
    }

}