package com.at3nas.ludya.presentation.logIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.logIn.model.Login
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.SplashHeader
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.form.FormInput

// View | Login //
@Composable
fun LoginView(
    navigateToHome: () -> Unit = {},
    logInViewModel: LogInViewModel = hiltViewModel()
) {
    // VARIABLES //
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    ColumnContainer {
        // HEADER //
        SplashHeader(
            headerIcon = null,
            headerTitle = stringResource(id = R.string.login),
            headerDesc = null,
            iconSize = 0.dp,
            modifier = Modifier
        )
        Column() {
            // FORM //
            FormInput(
                label = stringResource(id = R.string.email),
                leadingIcon = { Icon(Icons.Filled.Email, null) },
                isPassword = false,
                value = email,
                onValueChange = {
                    email = it
                }
            )

            FormInput(
                label = stringResource(id = R.string.password),
                leadingIcon = { Icon(Icons.Filled.Lock, null) },
                isPassword = true,
                value = password,
                onValueChange = {
                    password = it
                }
            )
        }
        Spacer(Modifier.height(16.dp))
        ActionButton(
            label = stringResource(id = R.string.login),
            buttonType = ButtonType.FILLED,
            onClick = {
                val user = Login(
                    email = email,
                    password = password
                )
                logInViewModel.logInWithEmail(user, navigateToHome)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true //authState.value != AuthState.Loading,
        )

        // TESTING: button to directly log in for quick tests //
        Spacer(
            modifier = Modifier.size(24.dp)
        )
        ActionButton(
            label = "TESTING - STUDENT",
            buttonType = ButtonType.FILLED,
            onClick = {
                val user = Login(
                    email = "at3nas@student.com",
                    password = "123abc"
                )
                logInViewModel.logInWithEmail(user, navigateToHome)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        )

        ActionButton(
            label = "TESTING - TEACHER",
            buttonType = ButtonType.FILLED,
            onClick = {
                val user = Login(
                    email = "at3nas@teacher.com",
                    password = "123abc"
                )
                logInViewModel.logInWithEmail(user, navigateToHome)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        )
    }
}