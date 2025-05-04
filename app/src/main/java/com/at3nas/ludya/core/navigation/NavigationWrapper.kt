package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.at3nas.ludya.presentation.ui.viewmodels.AuthViewModel
import com.at3nas.ludya.presentation.ui.views.HomeView
import com.at3nas.ludya.presentation.ui.views.LoginView
import com.at3nas.ludya.presentation.ui.views.RegisterView
import com.at3nas.ludya.presentation.ui.views.WelcomeView


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()

    NavHost(navController, startDestination = Welcome) {
        composable<Welcome> { WelcomeView({ navController.navigate(Register) }, { navController.navigate(Login) }) }
        composable<Register> { RegisterView(authViewModel) }
        composable<Login> { LoginView({ navController.navigate(Home) }, authViewModel) }
        composable<Home> { HomeView() }
    }
}