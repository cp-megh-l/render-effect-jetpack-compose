package com.example.rendereffectsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rendereffectsample.ui.theme.RenderEffectSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RenderEffectSampleTheme {

                Scaffold(
                    floatingActionButton = {
                        ExtendedFabRenderEffect()
                    }
                ) {
                    val paddings = it
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextRenderEffect()
                        //ImageRenderEffect()
                    }
                }
            }
        }
    }
}