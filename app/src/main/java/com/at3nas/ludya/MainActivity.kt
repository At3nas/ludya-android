package com.at3nas.ludya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.at3nas.ludya.core.navigation.NavigationWrapper
import com.at3nas.ludya.presentation.ui.LudyaTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(
) {
    //private val authViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LudyaTheme {
                NavigationWrapper()
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if (authViewModel.getCurrentUser() != null) {
//
//        }
//    }
}