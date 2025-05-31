package com.at3nas.ludya.presentation.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.NewUser
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ActionButtonIcon
import com.at3nas.ludya.presentation.ui.components.FormInput
import com.at3nas.ludya.presentation.ui.components.LudyaSurface
import com.at3nas.ludya.presentation.ui.components.SplashHeader
import com.at3nas.ludya.presentation.ui.components.Type


// View | Register //
@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    // VARIABLES //
    var email by rememberSaveable {
        mutableStateOf("")
    }

    var username by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    LudyaSurface {
        Column(
            content = {
                // HEADER //
                SplashHeader(
                    headerTitle = stringResource(id = R.string.register),
                    headerDesc = null,
                    headerIcon = null,
                    iconSize = 0.dp,
                    modifier = Modifier
                )

                // FORM //
                Column(
                    content = {
                        FormInput(
                            label = stringResource(id = R.string.username),
                            value = username,
                            leadingIcon = Icons.Filled.AccountCircle,
                            isPassword = false,
                            onValueChange = {
                                username = it
                            }
                        )

                        FormInput(
                            label = stringResource(id = R.string.email),
                            value = email,
                            leadingIcon = Icons.Filled.Email,
                            isPassword = false,
                            onValueChange = {
                                email = it
                            }
                        )

                        FormInput(
                            label = stringResource(id = R.string.password),
                            value = password,
                            leadingIcon = Icons.Filled.Lock,
                            isPassword = true,
                            onValueChange = {
                                password = it
                            }
                        )

                        FormInput(
                            label = stringResource(id = R.string.confirm_password),
                            value = confirmPassword,
                            leadingIcon = Icons.Filled.Lock,
                            isPassword = true,
                            onValueChange = {
                                confirmPassword = it
                            }
                        )
                    }
                )

                // BUTTONS //
                Column(
                    content = {
                        // REGISTER //
                        ActionButton(
                            label = stringResource(id = R.string.confirm),
                            contentDescription = stringResource(id = R.string.signup),
                            type = Type.FILLED,
                            onClick = {
                                // Creates new user //
                                val newUser = NewUser(
                                    email = email,
                                    password = password,
                                    username = username,
                                    confirmPassword = confirmPassword
                                )

                                signUpViewModel.signUpWithEmail(newUser)
                            },
                            enabled = true //authState.value != AuthState.Loading
                        )

                        // SIGN UP WITH GOOGLE //
                        ActionButtonIcon(
                            label = stringResource(id = R.string.signup_google),
                            contentDescription = stringResource(id = R.string.signup_google),
                            type = Type.OUTLINED,
                            icon = painterResource(id = R.drawable.google_icon),
                            onClick = {},
                            enabled = false
                        )
                    },
                    modifier = Modifier.padding(top = 25.dp)
                )

            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(25.dp, 25.dp)
        )
    }
}