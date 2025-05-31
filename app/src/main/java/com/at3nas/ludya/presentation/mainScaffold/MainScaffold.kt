package com.at3nas.ludya.presentation.mainScaffold

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.exploreCourses.ExploreView
import com.at3nas.ludya.presentation.home.HomeView
import com.at3nas.ludya.presentation.mainScaffold.components.TopBar
import com.at3nas.ludya.presentation.profile.ProfileView
import com.at3nas.ludya.presentation.testView.TestView


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
) {
    var scaffoldContent by rememberSaveable {
        mutableStateOf(
            MainScaffoldRoute.HOME
        )
    }

    Scaffold(
        topBar = { TopBar() },
        content = {
            when (scaffoldContent) {
                MainScaffoldRoute.HOME -> HomeView()
                MainScaffoldRoute.EXPLORE -> ExploreView()
                MainScaffoldRoute.PROFILE -> ProfileView()
                MainScaffoldRoute.TESTING -> TestView()
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
                                Icons.Filled.Home
                            ) { scaffoldContent = MainScaffoldRoute.HOME }

                            BottomBarItem(
                                stringResource(id = R.string.view_explore),
                                Icons.Filled.Search
                            ) { scaffoldContent = MainScaffoldRoute.EXPLORE }

                            BottomBarItem(
                                stringResource(id = R.string.view_profile),
                                Icons.Filled.AccountCircle
                            ) { scaffoldContent = MainScaffoldRoute.PROFILE }

                            // TESTING //
                            BottomBarItem(
                                "Testing",
                                Icons.Filled.Lock
                            ) { scaffoldContent = MainScaffoldRoute.TESTING }
                            // END TESTING //
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