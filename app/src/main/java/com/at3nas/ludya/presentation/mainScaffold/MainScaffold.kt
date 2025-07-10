package com.at3nas.ludya.presentation.mainScaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.presentation.createCourse.CreateCourseView
import com.at3nas.ludya.presentation.exploreCourses.ExploreCoursesView
import com.at3nas.ludya.presentation.home.HomeView
import com.at3nas.ludya.presentation.profile.ProfileView
import com.at3nas.ludya.presentation.quiz.QuizView

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    navigateToCourseView: (courseId: String) -> Unit,
    navigateToQuizView: (courseId: String, moduleId: String) -> Unit,
    navigateBack: () -> Unit,
    mainScaffoldViewModel: MainScaffoldViewModel = hiltViewModel()
) {
    var scaffoldContent by rememberSaveable {
        mutableStateOf(
            MainScaffoldRoute.HOME
        )
    }

    val isUserTeacher = mainScaffoldViewModel.isUserTeacher

    Scaffold(
        topBar = { /*TopBar()*/ },
        content = { innerPadding ->
            when (scaffoldContent) {
                MainScaffoldRoute.HOME -> HomeView(innerPadding)
                MainScaffoldRoute.PROFILE -> ProfileView(innerPadding)
                MainScaffoldRoute.ACADEMY -> CreateCourseView(
                    innerPadding,
                    navigateBack
                )
                MainScaffoldRoute.EXPLORE -> ExploreCoursesView(
                    navigateToCourseView,
                    navigateToQuizView,
                    innerPadding
                )
//                MainScaffoldRoute.TESTING -> QuizView("", innerPadding)
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            BottomBarItem(
                                stringResource(id = R.string.view_home),
                                painterResource(id = R.drawable.icon_house)
                            ) { scaffoldContent = MainScaffoldRoute.HOME }

                            BottomBarItem(
                                stringResource(id = R.string.view_explore),
                                painterResource(id = R.drawable.icon_search)
                            ) { scaffoldContent = MainScaffoldRoute.EXPLORE }

                            if (isUserTeacher) {
                                BottomBarItem(
                                    stringResource(id = R.string.view_academy),
                                    painterResource(id = R.drawable.icon_academy)
                                ) { scaffoldContent = MainScaffoldRoute.ACADEMY }
                            } else {
                                BottomBarItem(
                                    stringResource(id = R.string.view_library),
                                    painterResource(id = R.drawable.icon_mycourses)
                                ) { }
                            }

                            BottomBarItem(
                                stringResource(id = R.string.view_profile),
                                painterResource(id = R.drawable.icon_user)
                            ) { scaffoldContent = MainScaffoldRoute.PROFILE }

//                            BottomBarItem(
//                                "Testing",
//                                painterResource(id = R.drawable.icon_google)
//                            ) { scaffoldContent = MainScaffoldRoute.TESTING }
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun BottomBarItem(itemLabel: String, itemIcon: Painter, navigateTo: () -> Unit) {
    NavigationRailItem(
        icon = {
            Icon(
                painter = itemIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(30.dp)
            )
        },
        label = {
            Text(
                text = itemLabel,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        selected = false,
        onClick = {
            navigateTo.invoke()
        }
    )
}