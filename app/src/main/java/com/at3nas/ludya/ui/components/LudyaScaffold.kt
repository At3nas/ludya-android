package com.at3nas.ludya.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable


// Scaffold //
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LudyaScaffold() {
    Scaffold(
        topBar = { TopBar() },
        content = {},
        bottomBar = { BottomBar() }
    )
}