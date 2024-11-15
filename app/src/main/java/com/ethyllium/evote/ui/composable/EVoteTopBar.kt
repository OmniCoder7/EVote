package com.ethyllium.evote.ui.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EVoteTopBar(modifier: Modifier = Modifier,
                title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}