package com.at3nas.ludya.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.at3nas.ludya.presentation.WelcomeView
import com.at3nas.ludya.presentation.course.CourseView
import com.at3nas.ludya.presentation.createCourse.CreateCourseView
import com.at3nas.ludya.presentation.exploreCourses.ExploreCoursesView
import com.at3nas.ludya.presentation.logIn.LoginView
import com.at3nas.ludya.presentation.mainScaffold.MainScaffold
import com.at3nas.ludya.presentation.quiz.QuizView
import com.at3nas.ludya.presentation.signUp.SignUpView


@Composable
fun NavigationWrapper(
    navController: NavHostController = rememberNavController(),
    startDestination: Any
) {
    NavHost(navController, startDestination = startDestination) {
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
                navigateToCourseView = { courseId ->
                    navController.navigate("course/$courseId")
                },
                navigateToQuizView = { courseId, moduleId ->
                    navController.navigate("quiz/$courseId/$moduleId")
                },
                navigateBack = { navController.navigate(MainScaffold) }
            )
        }

        // Course
        composable(
            route = "course/{courseId}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val courseIdArgument = backStackEntry.arguments?.getString("courseId") ?: ""

            CourseView(
                courseId = courseIdArgument,
                navigateToQuizView = { courseId, moduleId ->
                    navController.navigate("quiz/$courseId/$moduleId")
                }
            )
        }

        // Quiz
        composable(
            route = "quiz/{courseId}/{moduleId}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType },
                navArgument("moduleId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("moduleId") ?: ""
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""

            QuizView(
                courseId = courseId,
                moduleId = moduleId,
                navigateBack = { navController.popBackStack() }
            )
        }

        composable<ExploreCourses> {
            ExploreCoursesView()
        }
    }
}