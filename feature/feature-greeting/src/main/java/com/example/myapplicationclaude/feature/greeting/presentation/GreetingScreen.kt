package com.example.myapplicationclaude.feature.greeting.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplicationclaude.core.ui.theme.MyApplicationClaudeTheme

@Composable
fun GreetingScreen(
    viewModel: GreetingViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    GreetingContent(
        state = state,
        onIntent = viewModel::handleIntent
    )
}

@Composable
internal fun GreetingContent(
    state: GreetingState,
    onIntent: (GreetingIntent) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = state.greetingMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingContentPreview() {
    MyApplicationClaudeTheme {
        GreetingContent(
            state = GreetingState(),
            onIntent = {}
        )
    }
}
