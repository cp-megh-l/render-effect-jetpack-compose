package com.example.rendereffectsample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.rendereffectsample.components.BlurContainer
import com.example.rendereffectsample.components.ShaderContainer
import kotlinx.coroutines.delay

@Composable
fun TextRenderEffect() {

    val animateTextList =
        listOf(
            "\"Reach your goals\"",
            "\"Achieve your dreams\"",
            "\"Be happy\"",
            "\"Be healthy\"",
            "\"Get rid of depression\"",
            "\"Overcome loneliness\""
        )

    var index by remember {
        mutableIntStateOf(0)
    }

    var textToDisplay by remember {
        mutableStateOf("")
    }

    val blur = remember { Animatable(0f) }

    LaunchedEffect(textToDisplay) {
        blur.animateTo(30f, tween(easing = LinearEasing))
        blur.animateTo(0f, tween(easing = LinearEasing))
    }

    LaunchedEffect(key1 = animateTextList) {
        while (index <= animateTextList.size) {
            textToDisplay = animateTextList[index]
            delay(3000)
            index = (index + 1) % animateTextList.size
        }
    }

    ShaderContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        BlurContainer(
            modifier = Modifier.fillMaxSize(),
            blur = blur.value,
            component = {
                AnimatedContent(
                    targetState = textToDisplay,
                    modifier = Modifier
                        .fillMaxWidth(),
                    transitionSpec = {
                        (scaleIn()).togetherWith(
                            scaleOut()
                        )
                    }, label = ""
                ) { text ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = text,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                }
            }
        ) {}
    }
}