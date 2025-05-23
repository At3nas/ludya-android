package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.at3nas.ludya.R

@Composable
fun BottomBar(
    navigateToHome: () -> Unit,
    navigateToExplore: () -> Unit,
    navigateToProfile: () -> Unit
) {
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
                    ) { navigateToHome.invoke() }

                    BottomBarItem(
                        stringResource(id = R.string.view_explore),
                        Icons.Filled.Search
                    ) { navigateToExplore.invoke() }

                    BottomBarItem(
                        stringResource(id = R.string.view_profile),
                        Icons.Filled.AccountCircle
                    ) { navigateToProfile.invoke() }
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