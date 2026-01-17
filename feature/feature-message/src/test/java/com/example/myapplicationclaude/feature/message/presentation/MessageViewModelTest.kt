package com.example.myapplicationclaude.feature.message.presentation

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MessageViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MessageViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MessageViewModel()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @DisplayName("Initial state should have default message")
    fun initialState() = runTest {
        viewModel.state.test {
            val initialState = awaitItem()
            assertEquals("message", initialState.message)
        }
    }

    @Test
    @DisplayName("ViewModel should maintain state when no intents are handled")
    fun stateRemainsSame() = runTest {
        viewModel.state.test {
            val state1 = awaitItem()
            assertEquals("message", state1.message)

            // No state changes expected
            expectNoEvents()
        }
    }
}
