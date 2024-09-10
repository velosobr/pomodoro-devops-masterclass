package com.velosobr.pomodoro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.pomodoro.intent.PomodoroIntent
import com.velosobr.pomodoro.model.PomodoroState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PomodoroViewModel : ViewModel() {
    private val _state = MutableStateFlow(PomodoroState())
    val state: StateFlow<PomodoroState> get() = _state

    fun handleIntent(intent: PomodoroIntent) {
        viewModelScope.launch {
            when (intent) {
                is PomodoroIntent.Start -> _state.value = _state.value.copy(
                    isRunning = true,
                    timeLeft =
                    if (_state.value.timeLeft == 0L) 25 * 60 * 1000L
                    else _state.value.timeLeft
                )

                is PomodoroIntent.Pause -> _state.value = _state.value.copy(isRunning = false)
                is PomodoroIntent.Stop -> _state.value = _state.value.copy(
                    isRunning = false,
                    timeLeft = 0L
                )
            }
        }
    }
}