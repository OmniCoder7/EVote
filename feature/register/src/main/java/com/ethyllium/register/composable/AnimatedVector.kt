package com.ethyllium.register.composable

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVector(
    modifier: Modifier = Modifier,
    id: Int,
    state: Boolean,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null
) {
    val image = AnimatedImageVector.animatedVectorResource(id)
    Image(
        painter = rememberAnimatedVectorPainter(image, state),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}