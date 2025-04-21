package com.at3nas.ludya.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.ui.components.FormInput
import com.at3nas.ludya.ui.theme.BgColor
import com.at3nas.ludya.ui.theme.LudyaTheme


// View | Register //
@Composable
fun RegisterView() {
    Surface(
        content = {
            Column(
                content = {
                    FormInput()
                },
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            )
        },
        color = BgColor,
        modifier = Modifier.fillMaxSize()
    )
}

// Preview //
@Preview
@Composable
fun TestingPreview() {
    LudyaTheme {
        RegisterView()
    }
}