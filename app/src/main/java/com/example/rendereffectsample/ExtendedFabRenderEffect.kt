package com.example.rendereffectsample

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.rendereffectsample.components.BlurContainer
import com.example.rendereffectsample.components.ShaderContainer

@Composable
fun ExtendedFabRenderEffect() {

    var expanded: Boolean by remember {
        mutableStateOf(false)
    }

    val alpha by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = ""
    )

    ShaderContainer(
        modifier = Modifier.fillMaxSize()
    ) {

        ButtonComponent(
            Modifier.padding(
                paddingValues = PaddingValues(
                    bottom = 80.dp
                ) * FastOutSlowInEasing
                    .transform((alpha))
            ),
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.alpha(alpha)
            )
        }

        ButtonComponent(
            Modifier.padding(
                paddingValues = PaddingValues(
                    bottom = 160.dp
                ) * FastOutSlowInEasing.transform(alpha)
            ),
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.alpha(alpha)
            )
        }

        ButtonComponent(
            Modifier.padding(
                paddingValues = PaddingValues(
                    bottom = 240.dp
                ) * FastOutSlowInEasing.transform(alpha)
            ),
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.alpha(alpha)
            )
        }

        ButtonComponent(
            Modifier.align(Alignment.BottomEnd),
            onClick = {
                expanded = !expanded
            },
        ) {
            val rotation by animateFloatAsState(
                targetValue = if (expanded) 45f else 0f,
                label = "",
                animationSpec = tween(1000, easing = FastOutSlowInEasing)
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.rotate(rotation),
                tint = Color.White
            )
        }
    }
}

@Composable
fun BoxScope.ButtonComponent(
    modifier: Modifier = Modifier,
    background: Color = Color.Black,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    BlurContainer(
        modifier = modifier
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = onClick,
            )
            .align(Alignment.BottomEnd),
        component = {
            Box(
                Modifier
                    .size(40.dp)
                    .background(color = background, CircleShape)
            )
        }
    ) {
        Box(
            Modifier.size(80.dp),
            content = content,
            contentAlignment = Alignment.Center,
        )
    }
}

private operator fun PaddingValues.times(factor: Float): PaddingValues {
    return PaddingValues(
        start = this.calculateStartPadding(LayoutDirection.Ltr) * factor,
        end = this.calculateEndPadding(LayoutDirection.Ltr) * factor,
        top = this.calculateTopPadding() * factor,
        bottom = this.calculateBottomPadding() * factor
    )
}