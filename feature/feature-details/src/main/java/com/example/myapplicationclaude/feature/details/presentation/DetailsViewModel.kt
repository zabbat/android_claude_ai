package com.example.myapplicationclaude.feature.details.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(DetailsState(currentDate = getCurrentDate()))
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun handleIntent(intent: DetailsIntent) {
        // No intents to handle currently
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
