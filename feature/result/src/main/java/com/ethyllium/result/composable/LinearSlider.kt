package com.ethyllium.result.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LinearSlider(
    modifier: Modifier = Modifier,
    fraction: Float,
    strokeWidth: Float = 40f,
    cap: StrokeCap = StrokeCap.Round,
    progressColor: Color,
    progressBackgroundColor: Color = Color.Gray
) {
    val progress = animateFloatAsState(fraction, label = "")
    Canvas(modifier = modifier.fillMaxSize()) {
        drawLine(
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            color = progressBackgroundColor,
            strokeWidth = strokeWidth,
            cap = cap
        )
        drawLine(
            start = Offset(0f, size.height / 2),
            end = Offset(size.width * progress.value, size.height / 2),
            color = progressColor,
            strokeWidth = strokeWidth,
            cap = cap
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LInearSlider() {
    LinearSlider(progressColor = Color.Red, modifier = Modifier.padding(horizontal = 10.dp),
        fraction = 0.5f)
}