package com.at3nas.ludya.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.ui.components.ActionButton
import com.at3nas.ludya.ui.components.LudyaSurface
import com.at3nas.ludya.ui.components.SplashHeader
import com.at3nas.ludya.ui.components.Type
import com.at3nas.ludya.ui.theme.LudyaTheme


@Composable
fun WelcomeView(navigateToRegister: () -> Unit, navigateToLogin: () -> Unit) {
    LudyaSurface {
        Column(
            content = {
                SplashHeader(
                    headerIcon = painterResource(id = R.drawable.ludya_logo),
                    headerTitle = stringResource(id = R.string.welcome_title),
                    headerDesc = stringResource(id = R.string.welcome_description),
                    modifier = Modifier.padding(bottom = 25.dp),
                    iconSize = 150.dp
                )

                Column {
                    ActionButton(
                        label = stringResource(id = R.string.signup),
                        contentDescription = stringResource(id = R.string.signup),
                        type = Type.FILLED,
                        onClick = navigateToRegister
                    )
                    ActionButton(
                        label = stringResource(id = R.string.login),
                        contentDescription = stringResource(id = R.string.login),
                        type = Type.OUTLINED,
                        onClick = navigateToLogin
                    )
                }

            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(25.dp, 25.dp)
        )
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