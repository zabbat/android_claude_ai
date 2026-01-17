package com.example.myapplicationclaude.feature.greeting.presentation

sealed interface GreetingIntent {
    data class UpdateName(val name: String) : GreetingIntent
}

data class GreetingState(
    val name: String = "Android",
    val greetingMessage: String = "Hello Android!"
)

sealed interface GreetingEffect {
    data class ShowToast(val message: String) : GreetingEffect
}
