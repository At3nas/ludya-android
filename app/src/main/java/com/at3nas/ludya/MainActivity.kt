package com.at3nas.ludya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.core.navigation.MainScaffold
import com.at3nas.ludya.core.navigation.NavigationWrapper
import com.at3nas.ludya.core.navigation.Welcome
import com.at3nas.ludya.presentation.ui.LudyaTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()

            LudyaTheme {
//                if (mainViewModel.isUserAuthenticated) {
//                    NavigationWrapper(startDestination = MainScaffold)
//                } else {
//                    NavigationWrapper(startDestination = Welcome)
//                }
                NavigationWrapper(startDestination = Welcome)
            }
        }
    }

}