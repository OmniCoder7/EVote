package com.ethyllium.register.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T>GroupedRadioButton(items: List<T>, onClick: (T) -> Unit, selectedItem: T) {
    Column {
        items.forEach { item ->
            Row {
                RadioButton(
                    selected = selectedItem == item,
                    onClick = { onClick(item) },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Magenta)
                )
            }
            Text(text = item.toString(), modifier = Modifier.padding(start = 8.dp))
        }
    }
}