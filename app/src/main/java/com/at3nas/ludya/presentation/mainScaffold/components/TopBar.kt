package com.at3nas.ludya.presentation.mainScaffold.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign


// TopBar //
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Ludya", textAlign = TextAlign.Center)
        },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Filled.Menu, null) },
                onClick = {}
            )
        }
    )
}