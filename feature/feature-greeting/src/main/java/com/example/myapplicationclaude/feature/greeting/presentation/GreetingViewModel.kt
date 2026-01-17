package com.example.myapplicationclaude.feature.greeting.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GreetingViewModel : ViewModel() {

    private val _state = MutableStateFlow(GreetingState())
    val state: StateFlow<GreetingState> = _state.asStateFlow()

    fun handleIntent(intent: GreetingIntent) {
        when (intent) {
            is GreetingIntent.UpdateName -> updateName(intent.name)
        }
    }

    private fun updateName(name: String) {
        _state.update { currentState ->
            currentState.copy(
                name = name,
                greetingMessage = "Hello $name!"
            )
        }
    }
}
