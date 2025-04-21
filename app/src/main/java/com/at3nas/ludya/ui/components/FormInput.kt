package com.at3nas.ludya.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun FormInput(inputLabel: String, inputValue: String) {
    TextField(
        label = { Text(inputLabel) },
        value = TextFieldValue(inputValue),
        onValueChange = {}
    )
}