package com.velosobr.pomodoro.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenericButton(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    contentColor: Color = Color.White,
    fullWidth: Boolean = false

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(paddingValues)
            .height(48.dp)
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier.width(130.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
@Preview
fun GenericButtonPreview() {
    GenericButton(
        text = "stop timer",
        onClick = {},
        backgroundColor = MaterialTheme.colorScheme.primary
    )

}