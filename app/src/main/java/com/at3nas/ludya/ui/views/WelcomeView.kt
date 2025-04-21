package com.at3nas.ludya.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.models.ButtonType
import com.at3nas.ludya.ui.components.ActionButton
import com.at3nas.ludya.ui.components.SplashHeader
import com.at3nas.ludya.ui.theme.BgColor
import com.at3nas.ludya.ui.theme.LudyaTheme


@Composable
fun WelcomeView(navigateToView: () -> Unit) {
    Surface(
        content = {
            Column(
                content = {
                    SplashHeader(
                        painterResource(id = R.drawable.ludya_logo),
                        "Ludya",
                        "Adventures full of learnings await",
                        Modifier.padding(bottom = 25.dp)
                    )

                    Column {
                        ActionButton("Sign Up", ButtonType.FILLED, navigateToView)
                        //ActionButton("Log In", ButtonType.OUTLINED)
                    }

                },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(25.dp, 25.dp)
            )
        },
        color = BgColor,
        modifier = Modifier.fillMaxSize(),

        )
}

@Preview
@Composable
fun Preview() {
    LudyaTheme {
        WelcomeView({})
    }
}