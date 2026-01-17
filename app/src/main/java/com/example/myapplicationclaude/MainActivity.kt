package com.example.myapplicationclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplicationclaude.core.ui.theme.MyApplicationClaudeTheme
import com.example.myapplicationclaude.feature.greeting.presentation.GreetingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationClaudeTheme {
                GreetingScreen()
            }
        }
    }
}