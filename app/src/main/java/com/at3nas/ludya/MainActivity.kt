package com.at3nas.ludya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.core.navigation.NavigationWrapper
import com.at3nas.ludya.presentation.ui.theme.LudyaTheme


// Main //
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LudyaTheme {
                NavigationWrapper()
            }
        }
    }
}

// Preview //
@Preview
@Composable
fun TestingPreview() {
    LudyaTheme {

    }
}

// Function | Capitalize string //
fun capitalizeText(text: String): String {
    if (text[0].isLowerCase()) {
        return text.replaceFirstChar {
            it.uppercase()
        }
    }
    return text
}