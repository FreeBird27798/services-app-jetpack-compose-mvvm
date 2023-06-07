package com.taj.servicesapp.view.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.taj.servicesapp.ui.theme.Blue
import com.taj.servicesapp.ui.theme.DarkBlue00

@Composable
fun CurvedBox(
    content: @Composable () -> Unit = {},
    gradientColors: List<Color> = listOf(Blue, DarkBlue00),
    height: Dp = 150.dp,
    curveEndYRatio: Float = 1f,
    curveControlYRatio: Float = 1.1f,
    x1: Float = 0.60f,
    x2: Float = 0.40f,
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = gradientColors
    )
    Box(
        modifier = Modifier
            .padding(bottom = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            val curveStartY = 0f
            val curveEndY = size.height * curveEndYRatio
            val curveControlY = size.height * curveControlYRatio

            val path = Path().apply {
                moveTo(0f, curveStartY)
                lineTo(size.width, curveStartY)
                lineTo(size.width, curveEndY)
                cubicTo(
                    size.width * x1, curveControlY,
                    size.width * x2, curveControlY,
                    0f, curveEndY
                )
                close()
            }

            drawPath(
                path = path,
                brush = gradientBrush
            )
        }
        content()
    }
}