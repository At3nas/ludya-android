package com.at3nas.ludya.presentation.logIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.ExistingUser
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.FormInput
import com.at3nas.ludya.presentation.ui.components.LudyaSurface
import com.at3nas.ludya.presentation.ui.components.SplashHeader
import com.at3nas.ludya.presentation.ui.components.Type
import com.at3nas.ludya.presentation.ui.LudyaTheme

// View | Login //
@Composable
fun LoginView(
    navigateToHome: () -> Unit,
    logInViewModel: LogInViewModel = hiltViewModel()
) {
    // VARIABLES //
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

//    val authState = authService.authState.observeAsState()
//
//    when (authState.value) {
//        is AuthState.Authenticated -> navigateToHome()
//        is AuthState.Error -> print("")
//        else -> Unit
//    }

    LudyaSurface {
        Column(
            content = {
                // HEADER //
                SplashHeader(
                    headerIcon = null,
                    headerTitle = stringResource(id = R.string.login),
                    headerDesc = null,
                    iconSize = 0.dp,
                    modifier = Modifier
                )

                // FORM //
                FormInput(
                    label = stringResource(id = R.string.email),
                    leadingIcon = Icons.Filled.Email,
                    isPassword = false,
                    value = email,
                    onValueChange = {
                        email = it
                    }
                )

                FormInput(
                    label = stringResource(id = R.string.password),
                    leadingIcon = Icons.Filled.Lock,
                    isPassword = true,
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )

                ActionButton(
                    label = stringResource(id = R.string.login),
                    contentDescription = stringResource(id = R.string.login),
                    type = Type.FILLED,
                    onClick = {
                        val user = ExistingUser(
                            email = email,
                            password = password
                        )
                        logInViewModel.logInWithEmail(user, navigateToHome)
                    },
                    enabled = true //authState.value != AuthState.Loading,
                )
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(25.dp)
        )
    }
}

@Preview
@Composable
fun LoginViewPreview() {
    LudyaTheme {
        //LoginView()
    }
}