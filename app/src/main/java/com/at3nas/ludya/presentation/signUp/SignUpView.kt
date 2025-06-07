package com.at3nas.ludya.presentation.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.user.UserRole
import com.at3nas.ludya.presentation.signUp.model.SignUp
import com.at3nas.ludya.presentation.ui.BgColor
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ActionButtonIcon
import com.at3nas.ludya.presentation.ui.components.FormInput
import com.at3nas.ludya.presentation.ui.components.SplashHeader
import com.at3nas.ludya.presentation.ui.components.Type
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


// View | Register //
@Composable
fun SignUpView(
    navigateToHome: () -> Unit,
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

    val radioOptions = listOf(R.string.student, R.string.teacher)
    val (selectedOption, onOptionSelected) = rememberSaveable {
        mutableIntStateOf(radioOptions[0])
    }

    ColumnContainer {
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
                    leadingIcon = { Icon(Icons.Filled.AccountCircle, null) },
                    isPassword = false,
                    onValueChange = {
                        username = it
                    }
                )

                FormInput(
                    label = stringResource(id = R.string.email),
                    value = email,
                    leadingIcon = { Icon(Icons.Filled.Email, null) },
                    isPassword = false,
                    onValueChange = {
                        email = it
                    }
                )

                FormInput(
                    label = stringResource(id = R.string.password),
                    value = password,
                    leadingIcon = { Icon(Icons.Filled.Lock, null) },
                    isPassword = true,
                    onValueChange = {
                        password = it
                    }
                )

                FormInput(
                    label = stringResource(id = R.string.confirm_password),
                    value = confirmPassword,
                    leadingIcon = { Icon(Icons.Filled.Lock, null) },
                    isPassword = true,
                    onValueChange = {
                        confirmPassword = it
                    }
                )

                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    radioOptions.forEach { option ->
                        Row(
                            Modifier
                                .selectable(
                                    selected = (option == selectedOption),
                                    onClick = { onOptionSelected(option) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (option == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = stringResource(option),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
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
                        val newUser = SignUp(
                            email = email,
                            password = password,
                            username = username,
                            confirmPassword = confirmPassword,
                            role = if (selectedOption == radioOptions[0]) {
                                UserRole.STUDENT
                            } else {
                                UserRole.TEACHER
                            }
                        )

                        signUpViewModel.signUpWithEmail(newUser, navigateToHome)
                    },
                    enabled = true //authState.value != AuthState.Loading
                )

                // SIGN UP WITH GOOGLE //
                ActionButtonIcon(
                    label = stringResource(id = R.string.signup_google),
                    contentDescription = stringResource(id = R.string.signup_google),
                    type = Type.OUTLINED,
                    icon = painterResource(id = R.drawable.icon_google),
                    iconColor = BgColor,
                    iconSize = 20.dp,
                    space = 5.dp,
                    onClick = {},
                    enabled = false
                )
            },
            modifier = Modifier.padding(top = 25.dp)
        )
    }
}