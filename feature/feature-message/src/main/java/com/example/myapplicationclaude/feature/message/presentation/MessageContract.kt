package com.example.myapplicationclaude.feature.message.presentation

sealed interface MessageIntent

data class MessageState(
    val message: String = "message"
)

sealed interface MessageEffect
