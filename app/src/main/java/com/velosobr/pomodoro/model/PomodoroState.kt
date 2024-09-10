package com.velosobr.pomodoro.model

data class PomodoroState(
    val isRunning: Boolean = false,
    val timeLeft: Long = 25 * 60 * 1000L
)