package com.at3nas.ludya.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.presentation.ui.components.LudyaSurface
import com.at3nas.ludya.presentation.ui.LudyaTheme


@Composable
fun HomeView(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val username = homeViewModel.updateUsername()

    LudyaTheme {
        LudyaSurface {
            Column(
                modifier = Modifier.padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text("Welcome back ${username}!")
                }
            )
        }
    }
}