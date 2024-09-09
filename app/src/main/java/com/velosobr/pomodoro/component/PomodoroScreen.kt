package com.velosobr.pomodoro.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.pomodoro.ui.theme.AppColors


@Composable
fun PomodoroScreen() {
    var key by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PomodoroTitle()

        PomodoroTimer(
            key = key,
            isRunning = isRunning
        )


        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GenericButton(

                text = "Start",
                paddingValues = PaddingValues(start = 16.dp,end = 8.dp, top = 16.dp),
                onClick = { isRunning = true },
                backgroundColor = AppColors.DraculaStart,
                fullWidth = false
            )

            GenericButton(
                text = "Stop",
                paddingValues = PaddingValues(start = 8.dp, end = 16.dp, top = 16.dp),
                onClick = { isRunning = false; key++ },
                backgroundColor = AppColors.DraculaStop,
                fullWidth = false
            )
        }
    }
}

@Composable
fun ProgressIndicator() {
    // Implementação do indicador de progresso
    CircularProgressIndicator(
        progress = 0.5f, // Exemplo de progresso
        modifier = Modifier.size(48.dp),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
@Preview
fun PomodoroScreenPreview() {
    PomodoroScreen()
}