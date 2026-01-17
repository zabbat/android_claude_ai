package com.example.myapplicationclaude.feature.details.presentation

import app.cash.turbine.test
import com.example.myapplicationclaude.feature.details.CoroutineTestExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class DetailsViewModelTest {

    private lateinit var viewModel: DetailsViewModel

    @BeforeEach
    fun setup() {
        viewModel = DetailsViewModel()
    }

    @Test
    @DisplayName("Initial state should have current date populated")
    fun initialState() = runTest {
        viewModel.state.test {
            val initialState = awaitItem()
            assertNotNull(initialState.currentDate)
            assertTrue(initialState.currentDate.isNotEmpty())
        }
    }

    @Test
    @DisplayName("Current date should be in expected format")
    fun currentDateFormat() = runTest {
        viewModel.state.test {
            val state = awaitItem()
            // Date format: "Day, Month DD, YYYY" e.g., "Friday, January 17, 2026"
            // Check that it contains a comma and spaces
            assertTrue(state.currentDate.contains(","))
            assertTrue(state.currentDate.contains(" "))
        }
    }

    @Test
    @DisplayName("ViewModel should maintain state when no intents are handled")
    fun stateRemainsSame() = runTest {
        viewModel.state.test {
            val state1 = awaitItem()
            assertNotNull(state1.currentDate)

            // No state changes expected
            expectNoEvents()
        }
    }
}
