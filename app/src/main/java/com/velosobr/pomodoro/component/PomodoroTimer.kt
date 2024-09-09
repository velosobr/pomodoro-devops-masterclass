package com.velosobr.pomodoro.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PomodoroTimer(
    modifier: Modifier = Modifier,
    totalTime: Long = 25 * 60 * 1000L,
    key: Int,
    isRunning: Boolean
) {
    var timeLeft by remember(key) { mutableLongStateOf(totalTime) }

    val animatedAngle by animateFloatAsState(
        targetValue = (1f - (timeLeft / totalTime.toFloat())) * 360f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "Timer Animation"
    )

    LaunchedEffect(key, isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft -= 1000L
            }
        }
    }

    val minutes = (timeLeft / 1000).toInt() / 60
    val seconds = (timeLeft / 1000).toInt() % 60
    val color = MaterialTheme.colorScheme.primary
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = modifier.size(250.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 2
            drawCircle(
                color = Color.LightGray,
                center = center,
                radius = radius,
                style = Stroke(width = 4.dp.toPx())
            )
            drawMinuteMarkers(center, radius)
            drawNumberMarkers(center, radius, color)
            val pointerLength = radius * 0.75f
            val pointerAngle = Math.toRadians(animatedAngle.toDouble() - 90)
            val pointerEnd = Offset(
                x = center.x + pointerLength * Math.cos(pointerAngle).toFloat(),
                y = center.y + pointerLength * Math.sin(pointerAngle).toFloat()
            )
            drawLine(
                color = Color(0xFFFF6347), // Tomate vermelho
                start = center,
                end = pointerEnd,
                strokeWidth = 3.dp.toPx()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        StyledTimerText(minutes = minutes, seconds = seconds)
    }
}

fun DrawScope.drawNumberMarkers(center: Offset, radius: Float, color: Color) {
    val numberRadius = radius * 0.8f
    val numbers = listOf(25, 5, 10, 15, 20)
    for (i in numbers.indices) {
        val angle = Math.toRadians(i * 72.0 - 90) // 72 degrees between each number (360/5)
        val x = center.x + numberRadius * cos(angle).toFloat()
        val y = center.y + numberRadius * sin(angle).toFloat()
        drawContext.canvas.nativeCanvas.drawText(
            numbers[i].toString(),
            x,
            y,
            android.graphics.Paint().apply {
                this.color = color.toArgb()
                textSize = 40f
                textAlign = android.graphics.Paint.Align.CENTER
            }
        )
    }
}

fun DrawScope.drawMinuteMarkers(center: Offset, radius: Float) {
    val markerLength = radius * 0.1f
    for (i in 0 until 60) {
        val angle = Math.toRadians(i * 6.0 - 90)
        val start = Offset(
            x = center.x + radius * cos(angle).toFloat(),
            y = center.y + radius * sin(angle).toFloat()
        )
        val end = Offset(
            x = center.x + (radius - markerLength) * cos(angle).toFloat(),
            y = center.y + (radius - markerLength) * sin(angle).toFloat()
        )
        drawLine(
            color = Color.Gray,
            start = start,
            end = end,
            strokeWidth = 2.dp.toPx()
        )
    }
}

@Composable
@Preview
fun PomodoroTimerPreview() {
    PomodoroTimer(
        totalTime = 25 * 60 * 1000L,
        key = 0,
        isRunning = false
    )
}
