package com.ethyllium.result.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TComposable(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    start: @Composable () -> Unit,
    endTop: @Composable () -> Unit,
    endBottom: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier.padding(24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            start.invoke()
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                endTop.invoke()
                endBottom.invoke()
            }
        }
    }
}
