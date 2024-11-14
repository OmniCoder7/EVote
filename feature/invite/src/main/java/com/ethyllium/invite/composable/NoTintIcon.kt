package com.ethyllium.invite.composable

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun NoTintIcon(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = id),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}