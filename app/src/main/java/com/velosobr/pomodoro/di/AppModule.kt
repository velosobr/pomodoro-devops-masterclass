package com.velosobr.pomodoro.di

import com.velosobr.pomodoro.viewmodel.PomodoroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PomodoroViewModel() }
}