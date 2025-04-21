package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.at3nas.ludya.ui.views.RegisterView
import com.at3nas.ludya.ui.views.WelcomeView


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Welcome) {
        composable<Welcome> { WelcomeView { navController.navigate(Register) } }
        composable<Register> { RegisterView() }
    }
}