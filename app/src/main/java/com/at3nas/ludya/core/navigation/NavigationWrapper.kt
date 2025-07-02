package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.at3nas.ludya.presentation.WelcomeView
import com.at3nas.ludya.presentation.courseView.CourseView
import com.at3nas.ludya.presentation.exploreCourses.ExploreCoursesView
import com.at3nas.ludya.presentation.logIn.LoginView
import com.at3nas.ludya.presentation.mainScaffold.MainScaffold
import com.at3nas.ludya.presentation.signUp.SignUpView


@Composable
fun NavigationWrapper(
    navController: NavHostController = rememberNavController()
) {
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
            MainScaffold(
                navigateToCourseView = { course ->
                    course?.courseId?.let { id ->
                        navController.navigate("courseView/$id")
                    }
                }
            )
        }

        composable<ExploreCourses> {
            ExploreCoursesView()
        }

        composable(
            route = "courseView/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            CourseView(courseId = courseId)
        }
    }
}