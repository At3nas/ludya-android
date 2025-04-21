package com.at3nas.ludya.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.models.ButtonType
import com.at3nas.ludya.ui.theme.LudyaTheme

@Composable
fun ActionButton(btnLabel: String, btnType: ButtonType, onClick: () -> Unit) {
    when (btnType) {
        ButtonType.FILLED -> {
            Button(
                content = {
                    Text(btnLabel)
                },
                modifier = Modifier.fillMaxWidth(),
                onClick = {onClick()},
            )
        }
        ButtonType.OUTLINED -> {
            OutlinedButton(
                content = {
                    Text(btnLabel)
                },
                modifier = Modifier.fillMaxWidth(),
                onClick = {onClick()},
            )
        }
    }

}

@Preview
@Composable
fun Testing() {
    LudyaTheme {
        ActionButton("Sign up", ButtonType.OUTLINED) { testFunction() }
    }
}

fun testFunction() {
    print("test")
}