package com.example.myapplicationclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplicationclaude.core.ui.theme.MyApplicationClaudeTheme
import com.example.myapplicationclaude.feature.greeting.presentation.GreetingScreen
import com.example.myapplicationclaude.feature.message.presentation.MessageScreen
import com.example.myapplicationclaude.feature.details.presentation.DetailsScreen
import kotlinx.serialization.Serializable

@Serializable
object GreetingRoute

@Serializable
data class MessageRoute(val name: String)

@Serializable
object DetailsRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationClaudeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = GreetingRoute
                ) {
                    composable<GreetingRoute> {
                        GreetingScreen(
                            onNavigateToMessage = { name ->
                                navController.navigate(MessageRoute(name))
                            },
                            onNavigateToDetails = {
                                navController.navigate(DetailsRoute)
                            }
                        )
                    }
                    composable<MessageRoute> { backStackEntry ->
                        val messageRoute = backStackEntry.toRoute<MessageRoute>()
                        MessageScreen(
                            name = messageRoute.name,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<DetailsRoute> {
                        DetailsScreen(
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