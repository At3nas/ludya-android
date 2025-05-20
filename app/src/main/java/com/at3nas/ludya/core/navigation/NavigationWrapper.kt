package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.at3nas.ludya.presentation.HomeView
import com.at3nas.ludya.presentation.logIn.LoginView
import com.at3nas.ludya.presentation.signUp.SignUpView
import com.at3nas.ludya.presentation.WelcomeView


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Welcome) {
        composable<Welcome> { WelcomeView({ navController.navigate(Register) }, { navController.navigate(Login) }) }
        composable<Register> { SignUpView() }
        composable<Login> { LoginView({ navController.navigate(Home) }) }
        composable<Home> { HomeView() }
    }
}