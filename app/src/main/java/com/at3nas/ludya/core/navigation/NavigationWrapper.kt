package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.at3nas.ludya.presentation.home.HomeView
import com.at3nas.ludya.presentation.logIn.LoginView
import com.at3nas.ludya.presentation.signUp.SignUpView
import com.at3nas.ludya.presentation.WelcomeView
import com.at3nas.ludya.presentation.explore.ExploreView
import com.at3nas.ludya.presentation.profile.ProfileView


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Welcome) {
        composable<Welcome> {
            WelcomeView(
                { navController.navigate(Register) },
                { navController.navigate(Login) }
            )
        }

        composable<Register> {
            SignUpView()
        }

        composable<Login> {
            LoginView({ navController.navigate(Home) })
        }

        composable<Home> {
            HomeView(
                { navController.navigate(Home) },
                { navController.navigate(Explore) },
                { navController.navigate(Profile) }
            )
        }

        composable<Explore> {
            ExploreView(
                { navController.navigate(Home) },
                { navController.navigate(Explore) },
                { navController.navigate(Profile) }
            )
        }

        composable<Profile> {
            ProfileView(
                { navController.navigate(Home) },
                { navController.navigate(Explore) },
                { navController.navigate(Profile) }
            )
        }
    }
}