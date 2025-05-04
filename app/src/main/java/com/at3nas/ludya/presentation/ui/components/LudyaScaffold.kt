package com.at3nas.ludya.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable


// Scaffold //
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LudyaScaffold(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = { TopBar() },
        content = content,
        bottomBar = { BottomBar() }
    )
}