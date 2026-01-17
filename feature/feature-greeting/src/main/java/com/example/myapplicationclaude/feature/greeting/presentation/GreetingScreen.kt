package com.example.myapplicationclaude.feature.greeting.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
    onNavigateToMessage: (String) -> Unit = {},
    viewModel: GreetingViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    GreetingContent(
        state = state,
        onIntent = viewModel::handleIntent,
        onNavigateToMessage = { onNavigateToMessage(state.name) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GreetingContent(
    state: GreetingState,
    onIntent: (GreetingIntent) -> Unit,
    onNavigateToMessage: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Greeting") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = state.greetingMessage)
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = state.name,
                onValueChange = { onIntent(GreetingIntent.UpdateName(it)) },
                label = { Text("Enter your name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToMessage) {
                Text("Go to Message")
            }
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
