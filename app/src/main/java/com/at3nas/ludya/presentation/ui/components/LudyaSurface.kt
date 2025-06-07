package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.at3nas.ludya.presentation.ui.BgColor

@Composable
fun LudyaSurface(content: @Composable () -> Unit) {
    Surface(
        content = content,
        color = BgColor,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
}