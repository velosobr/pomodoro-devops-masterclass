package com.velosobr.pomodoro.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.pomodoro.component.GenericButton
import com.velosobr.pomodoro.component.PomodoroTimer
import com.velosobr.pomodoro.component.PomodoroTitle
import com.velosobr.pomodoro.intent.PomodoroIntent
import com.velosobr.pomodoro.ui.theme.AppColors
import com.velosobr.pomodoro.viewmodel.PomodoroViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun PomodoroScreen(viewModel: PomodoroViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PomodoroTitle()

        PomodoroTimer(
            key = state.timeLeft.toInt(),
            isRunning = state.isRunning
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GenericButton(

                text = "Start",
                paddingValues = PaddingValues(start = 16.dp, end = 8.dp, top = 16.dp),
                onClick = { viewModel.handleIntent(PomodoroIntent.Start) },
                backgroundColor = AppColors.DraculaStart,
                fullWidth = false
            )

            GenericButton(
                text = "Pause",
                paddingValues = PaddingValues(start = 8.dp, end = 16.dp, top = 16.dp),
                onClick = { viewModel.handleIntent(PomodoroIntent.Pause) },
                backgroundColor = AppColors.DraculaPause,
                fullWidth = false
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        GenericButton(
            text = "Stop",
            onClick = { viewModel.handleIntent(PomodoroIntent.Stop) },
            backgroundColor = AppColors.DraculaStop,
            fullWidth = true
        )
    }
}

@Composable
@Preview
fun PomodoroScreenPreview() {
    PomodoroScreen()
}