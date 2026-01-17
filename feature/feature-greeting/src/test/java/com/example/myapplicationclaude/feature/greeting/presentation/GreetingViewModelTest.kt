package com.example.myapplicationclaude.feature.greeting.presentation

import app.cash.turbine.test
import com.example.myapplicationclaude.feature.greeting.CoroutineTestExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class GreetingViewModelTest {

    private lateinit var viewModel: GreetingViewModel

    @BeforeEach
    fun setup() {
        viewModel = GreetingViewModel()
    }

    @Test
    @DisplayName("Initial state should have default name and greeting message")
    fun initialState() = runTest {
        viewModel.state.test {
            val initialState = awaitItem()
            assertEquals("Android", initialState.name)
            assertEquals("Hello Android!", initialState.greetingMessage)
        }
    }

    @Test
    @DisplayName("UpdateName intent should update name and greeting message")
    fun updateName() = runTest {
        viewModel.state.test {
            // Skip initial state
            awaitItem()

            // Update name
            viewModel.handleIntent(GreetingIntent.UpdateName("John"))

            val updatedState = awaitItem()
            assertEquals("John", updatedState.name)
            assertEquals("Hello John!", updatedState.greetingMessage)
        }
    }

    @Test
    @DisplayName("UpdateName with empty string should update correctly")
    fun updateNameWithEmptyString() = runTest {
        viewModel.state.test {
            awaitItem()

            viewModel.handleIntent(GreetingIntent.UpdateName(""))

            val updatedState = awaitItem()
            assertEquals("", updatedState.name)
            assertEquals("Hello !", updatedState.greetingMessage)
        }
    }

    @Test
    @DisplayName("Multiple UpdateName intents should update state correctly")
    fun multipleUpdates() = runTest {
        viewModel.state.test {
            awaitItem() // Initial state

            viewModel.handleIntent(GreetingIntent.UpdateName("Alice"))
            val state1 = awaitItem()
            assertEquals("Alice", state1.name)
            assertEquals("Hello Alice!", state1.greetingMessage)

            viewModel.handleIntent(GreetingIntent.UpdateName("Bob"))
            val state2 = awaitItem()
            assertEquals("Bob", state2.name)
            assertEquals("Hello Bob!", state2.greetingMessage)
        }
    }
}
