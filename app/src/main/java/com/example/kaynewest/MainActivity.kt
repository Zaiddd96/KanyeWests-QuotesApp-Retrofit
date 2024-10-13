package com.example.kaynewest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.kaynewest.model.Quotes
import com.example.kaynewest.ui.theme.KayneWestTheme



class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KayneWestTheme {
                Scaffold {
                    val quote = remember { mutableStateOf(Quotes()) }
                    MyApp(quote)
                }
            }
        }
    }
}










