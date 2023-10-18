package com.example.rendereffectsample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rendereffectsample.components.BlurContainer
import com.example.rendereffectsample.components.ShaderContainer

@Composable
fun ImageRenderEffect() {

    var image by remember {
        mutableIntStateOf(R.drawable.ic_first)
    }

    val blur = remember { Animatable(0f) }

    LaunchedEffect(image) {
        blur.animateTo(100f, tween(easing = LinearEasing))
        blur.animateTo(0f, tween(easing = LinearEasing))
    }

    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        ShaderContainer(
            modifier = Modifier
                .animateContentSize()
                .clipToBounds()
        ) {

            BlurContainer(
                modifier = Modifier.fillMaxWidth(),
                blur = blur.value,
                component = {
                    AnimatedContent(
                        targetState = image,
                        modifier = Modifier
                            .fillMaxWidth(),
                        transitionSpec = {
                            (fadeIn(tween(easing = LinearEasing)) + scaleIn(
                                tween(
                                    1_000,
                                    easing = LinearEasing
                                )
                            )).togetherWith(
                                fadeOut(
                                    tween(
                                        1_000,
                                        easing = LinearEasing
                                    )
                                ) + scaleOut(
                                    tween(
                                        1_000,
                                        easing = LinearEasing
                                    )
                                )
                            )
                        }, label = ""
                    ) { image ->
                        Image(
                            painter = painterResource(id = image),
                            modifier = Modifier
                                .size(200.dp),
                            contentDescription = ""
                        )
                    }
                }) {}
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                image =
                    if (image == R.drawable.ic_first) R.drawable.ic_second else R.drawable.ic_first
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("Change Image")
        }
    }
}