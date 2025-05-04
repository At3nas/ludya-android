package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.presentation.ui.theme.BgColor
import com.at3nas.ludya.presentation.ui.theme.LudyaTheme

@Composable
fun FormInput(
    label: String,
    value: String,
    leadingIcon: ImageVector,
    isPassword: Boolean,
    onValueChange: (String) -> Unit
) {
    // Determines if the input is a password or not //
    val inputVisualTransformation: VisualTransformation = if (isPassword) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    // TextField //
    OutlinedTextField(
        label = { Text(label) },
        value = value,
        leadingIcon = { Icon(leadingIcon, null) },
        visualTransformation = inputVisualTransformation,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun FormInputPreview() {
    LudyaTheme {
        Surface(
            content = {
                //FormInput("Email", "", Icons.Filled.Email, false)
            },
            color = BgColor
        )
    }
}