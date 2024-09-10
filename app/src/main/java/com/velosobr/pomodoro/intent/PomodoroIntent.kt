package com.velosobr.pomodoro.intent

sealed class PomodoroIntent {
    data object Start : PomodoroIntent()
    data object Pause : PomodoroIntent()
    data object Stop : PomodoroIntent()
}