package com.example.myapplicationclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationclaude.core.ui.theme.MyApplicationClaudeTheme
import com.example.myapplicationclaude.feature.greeting.presentation.GreetingScreen
import com.example.myapplicationclaude.feature.message.presentation.MessageScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationClaudeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "greeting"
                ) {
                    composable("greeting") {
                        GreetingScreen(
                            onNavigateToMessage = {
                                navController.navigate("message")
                            }
                        )
                    }
                    composable("message") {
                        MessageScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}