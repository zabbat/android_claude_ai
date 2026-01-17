package com.example.myapplicationclaude.feature.message.presentation

import app.cash.turbine.test
import com.example.myapplicationclaude.feature.message.CoroutineTestExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class MessageViewModelTest {

    private lateinit var viewModel: MessageViewModel

    @BeforeEach
    fun setup() {
        viewModel = MessageViewModel()
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
