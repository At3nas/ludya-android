package com.at3nas.ludya
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.ui.theme.LudyaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LudyaTheme {
                MainContainer()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContainer() {
    Scaffold(
        topBar = { TopBar() },
        content = {},
        bottomBar = { NavigationBottomBar() }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Ludya", textAlign = TextAlign.Center)
        },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Filled.Menu, null) },
                onClick = {}
            )
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )

    )
}

@Composable
fun BottomMenuItem(itemLabel: String, itemIcon: ImageVector) {
    NavigationRailItem(
        icon = { Icon(itemIcon, null) },
        label = { Text(capitalizeText(itemLabel)) },
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
        onClick = {}
    )
}

@Composable
fun NavigationBottomBar() {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        content = {
            BottomMenuItem("Home", Icons.Filled.Home)
            BottomMenuItem("Explore", Icons.Filled.Search)
        }
    )
}

@Preview
@Composable
fun TestingPreview() {
    LudyaTheme {
        MainContainer()
    }
}


// Function | Capitalize string //
fun capitalizeText(text: String): String {
    if (text[0].isLowerCase()) {
        return text.replaceFirstChar{
            it.uppercase()
        }
    }
    return text
}