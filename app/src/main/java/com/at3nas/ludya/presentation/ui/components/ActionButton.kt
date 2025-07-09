package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


enum class ButtonType {
    FILLED,
    OUTLINED
}


@Composable
fun ActionButtonIcon(
    modifier: Modifier = Modifier,
    label: String = "",
    contentDescription: String = "",
    buttonType: ButtonType = ButtonType.FILLED,
    onClick: () -> Unit = {},
    icon: Painter = painterResource(R.drawable.icon_google),
    iconColor: Color = MaterialTheme.colorScheme.tertiary,
    enabled: Boolean = true
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        IconLabel(
            label = label,
            contentDescription = contentDescription,
            icon = icon,
            iconColor = iconColor,
            iconSize = 20.dp,
            space = 8.dp
        )
    }

    when (buttonType) {
        ButtonType.FILLED -> Button(
            content = content,
            onClick = { onClick() },
            enabled = enabled,
            modifier = modifier
        )

        ButtonType.OUTLINED -> OutlinedButton(
            content = content,
            onClick = { onClick() },
            enabled = enabled,
            modifier = modifier
        )
    }
}

@Composable
fun ActionButtonIcon(
    modifier: Modifier = Modifier,
    label: String = "",
    contentDescription: String = "",
    buttonType: ButtonType = ButtonType.FILLED,
    onClick: () -> Unit = {},
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward,
    enabled: Boolean = true
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        IconLabel(
            label = label,
            contentDescription = contentDescription,
            icon = icon,
            iconSize = 20.dp,
            space = 8.dp
        )
    }

    when (buttonType) {
        ButtonType.FILLED -> Button(
            content = content,
            onClick = { onClick() },
            enabled = enabled,
            modifier = modifier
        )

        ButtonType.OUTLINED -> OutlinedButton(
            content = content,
            onClick = { onClick() },
            enabled = enabled,
            modifier = modifier
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    label: String = "",
    buttonType: ButtonType = ButtonType.FILLED,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        Text(label)
    }

    when (buttonType) {
        ButtonType.FILLED -> {
            Button(
                content = content,
                onClick = { onClick() },
                enabled = enabled,
                modifier = modifier
            )
        }

        ButtonType.OUTLINED -> {
            OutlinedButton(
                content = content,
                onClick = { onClick() },
                enabled = enabled,
                modifier = modifier
            )
        }
    }
}


@Preview
@Composable
fun ButtonsTesting() {
    ColumnContainer {
        ActionButton(
            label = "Log In",
            buttonType = ButtonType.OUTLINED
        )
    }
}