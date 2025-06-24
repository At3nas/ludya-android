package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LudyaSurface(content: @Composable () -> Unit) {
    Surface(
        content = content,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
    )
}