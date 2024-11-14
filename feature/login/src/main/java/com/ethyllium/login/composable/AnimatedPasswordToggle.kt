package com.ethyllium.login.composable

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.ethyllium.login.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedPasswordToggle(modifier: Modifier = Modifier,
                              isPasswordVisible: Boolean,
                              onToggle: (Boolean) -> Unit) {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.password_show_hide)
    Image(
        painter = rememberAnimatedVectorPainter(image, isPasswordVisible),
        contentDescription = "Timer",
        modifier = modifier.clickable(indication = null, interactionSource = null) {
            onToggle(!isPasswordVisible)
        },
        contentScale = ContentScale.Crop
    )
}