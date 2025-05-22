package com.at3nas.ludya.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.surfaceModifier(color: Color) =
    fillMaxSize()
        .padding(25.dp)
        .background(color)

