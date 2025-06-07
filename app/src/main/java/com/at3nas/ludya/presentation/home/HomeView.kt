package com.at3nas.ludya.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Composable
fun HomeView(
    innerPadding: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val username = homeViewModel.updateUsername()
    val testUsername = "At3nas"

    ColumnContainer(
        modifier = Modifier.padding(innerPadding)
    ) {
        Text("¡Bienvenida a Ludya, ${username}!")
    }
}

