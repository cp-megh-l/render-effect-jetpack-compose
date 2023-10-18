package com.example.rendereffectsample.components

import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun BlurContainer(
    modifier: Modifier = Modifier,
    blur: Float = 60f,
    component: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .customBlur(blur),
            content = component,
        )
        Box(
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

fun Modifier.customBlur(blur: Float) = this.then(
    graphicsLayer {
        if (blur > 0f)
            renderEffect = RenderEffect
                .createBlurEffect(
                    blur,
                    blur,
                    Shader.TileMode.DECAL,
                )
                .asComposeRenderEffect()
    }
)
