package com.velosobr.pomodoro

import android.app.Application
import com.velosobr.pomodoro.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PomodoroApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PomodoroApplication)
            modules(appModule)
        }
    }
}