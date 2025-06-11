package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.at3nas.ludya.presentation.WelcomeView
import com.at3nas.ludya.presentation.logIn.LoginView
import com.at3nas.ludya.presentation.mainScaffold.MainScaffold
import com.at3nas.ludya.presentation.signUp.SignUpView


@Composable
fun NavigationWrapper(
    navController: NavHostController = rememberNavController()
) {
//    val bottomBarNavList = listOf(
//        {navController.navigate(Home)},
//        {navController.navigate(Explore)},
//        {navController.navigate(Profile)}
//    )

    NavHost(navController, startDestination = Welcome) {
        composable<Welcome> {
            WelcomeView(
                { navController.navigate(Register) },
                { navController.navigate(Login) }
            )
        }

        composable<Register> {
            SignUpView({ navController.navigate(MainScaffold) })
        }

        composable<Login> {
            LoginView({ navController.navigate(MainScaffold) })
        }

        composable<MainScaffold> {
            MainScaffold()
        }

//        composable<Home> {
//            HomeView()
//        }
//
//        composable<Explore> {
//            ExploreView()
//        }
//
//        composable<Profile> {
//            ProfileView()
//        }

        // STUDENT //


        // TEACHER //
//        composable<CreateCourse> {
//            CreateCourse()
//        }

    }
}