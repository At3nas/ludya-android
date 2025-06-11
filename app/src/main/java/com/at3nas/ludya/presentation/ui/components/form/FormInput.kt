package com.at3nas.ludya.presentation.ui.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun FormInput(
    label: String? = null,
    value: String,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    isPassword: Boolean = false,
    isReadOnly: Boolean = false,
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
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
        label = if (label != null) {
            { Text(label) }
        } else {
            null
        },
        value = value,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = isEnabled,
        readOnly = isReadOnly,
        singleLine = singleLine,
        visualTransformation = inputVisualTransformation,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onValueChange
    )
}