package com.at3nas.ludya.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.LudyaTheme
import com.at3nas.ludya.presentation.ui.components.LudyaScaffold
import com.at3nas.ludya.presentation.ui.components.LudyaSurface


@Composable
fun ProfileView(
    navigateToHome: () -> Unit,
    navigateToExplore: () -> Unit,
    navigateToProfile: () -> Unit
) {
    LudyaTheme {
        LudyaScaffold(
            content = { ProfileContent() },
            navigateToHome = navigateToHome,
            navigateToExplore = navigateToExplore,
            navigateToProfile = navigateToProfile
        )
    }
}

@Composable
fun ProfileContent() {
    LudyaSurface {
        Column(
            modifier = Modifier.padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Text("Profile View")
            }
        )
    }
}