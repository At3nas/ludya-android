package com.at3nas.ludya.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.components.LudyaScaffold
import com.at3nas.ludya.presentation.ui.components.LudyaSurface
import com.at3nas.ludya.presentation.ui.theme.LudyaTheme

@Composable
fun HomeView() {
    LudyaTheme {
        LudyaScaffold(
            content = {
                HomeContent()
            }
        )
    }
}

@Composable
fun HomeContent() {
    LudyaSurface {
        Column(
            modifier = Modifier.padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Text("Welcome back {username}!")
            }
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    LudyaTheme {
        HomeView()
    }
}