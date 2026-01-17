package com.example.myapplicationclaude.feature.details.presentation

sealed interface DetailsIntent

data class DetailsState(
    val currentDate: String = ""
)

sealed interface DetailsEffect
