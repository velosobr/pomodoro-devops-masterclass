package com.velosobr.pomodoro.viewmodel

import com.velosobr.pomodoro.intent.PomodoroIntent
import com.velosobr.pomodoro.model.PomodoroState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PomodoroViewModelTest {
    private lateinit var viewModel: PomodoroViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PomodoroViewModel()
    }

    @Test
    fun testStartIntent() = runTest {
        viewModel.handleIntent(PomodoroIntent.Start)
        assertEquals(true, viewModel.state.value.isRunning)
    }

    @Test
    fun testPauseIntent() = runTest {
        viewModel.handleIntent(PomodoroIntent.Pause)
        assertEquals(false, viewModel.state.value.isRunning)
    }

    @Test
    fun testStopIntent() = runTest {
        viewModel.handleIntent(PomodoroIntent.Stop)
        assertEquals(PomodoroState(isRunning = false, timeLeft = 0L), viewModel.state.value)
    }
}