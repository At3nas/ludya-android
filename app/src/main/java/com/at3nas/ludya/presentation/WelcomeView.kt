package com.at3nas.ludya.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.ui.LudyaTheme
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.SplashHeader
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


@Composable
fun WelcomeView(navigateToRegister: () -> Unit, navigateToLogin: () -> Unit) {
    ColumnContainer {
        SplashHeader(
            headerIcon = painterResource(id = R.drawable.logo_ludya),
            headerTitle = stringResource(id = R.string.welcome_title),
            headerDesc = stringResource(id = R.string.welcome_description),
            modifier = Modifier.padding(bottom = 25.dp),
            iconSize = 150.dp
        )

        Column {
            ActionButton(
                label = stringResource(id = R.string.signup),
                buttonType = ButtonType.FILLED,
                onClick = navigateToRegister,
                enabled = true
            )
            ActionButton(
                label = stringResource(id = R.string.login),
                buttonType = ButtonType.OUTLINED,
                onClick = navigateToLogin,
                enabled = true
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    LudyaTheme {
        WelcomeView({ testing() }, { testing() })
    }
}

fun testing() {

}