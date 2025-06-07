package com.at3nas.ludya.presentation.mainScaffold

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.createCourse.CreateCourse
import com.at3nas.ludya.presentation.exploreCourses.ExploreView
import com.at3nas.ludya.presentation.home.HomeView
import com.at3nas.ludya.presentation.mainScaffold.components.TopBar
import com.at3nas.ludya.presentation.profile.ProfileView

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    mainScaffoldViewModel: MainScaffoldViewModel = hiltViewModel()
) {
    var scaffoldContent by rememberSaveable {
        mutableStateOf(
            MainScaffoldRoute.HOME
        )
    }

    val isUserTeacher = mainScaffoldViewModel.isUserTeacher

    Scaffold(
        topBar = { TopBar() },
        content = { innerPadding ->
            when (scaffoldContent) {
                MainScaffoldRoute.HOME -> HomeView(innerPadding)
                MainScaffoldRoute.EXPLORE -> ExploreView(innerPadding)
                MainScaffoldRoute.PROFILE -> ProfileView(innerPadding)
                MainScaffoldRoute.ACADEMY -> CreateCourse()
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
                                ) {  }
                            }

                            BottomBarItem(
                                stringResource(id = R.string.view_profile),
                                painterResource(id = R.drawable.icon_user)
                            ) { scaffoldContent = MainScaffoldRoute.PROFILE }
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun BottomBarItem(itemLabel: String, itemIcon: ImageVector, navigateTo: () -> Unit) {
    NavigationRailItem(
        icon = { Icon(itemIcon, null) },
        label = { Text(itemLabel) },
        selected = false,
        colors = NavigationRailItemColors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            disabledIconColor = MaterialTheme.colorScheme.onPrimary,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = {
            navigateTo.invoke()
        }
    )
}

@Composable
fun BottomBarItem(itemLabel: String, itemIcon: Painter, navigateTo: () -> Unit) {
    NavigationRailItem(
        icon = { Icon(itemIcon, null, Modifier.size(30.dp)) },
        label = { Text(itemLabel) },
        selected = false,
        colors = NavigationRailItemColors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            disabledIconColor = MaterialTheme.colorScheme.onPrimary,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = {
            navigateTo.invoke()
        }
    )
}