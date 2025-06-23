package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.LudyaTheme


enum class ButtonType {
    FILLED,
    OUTLINED
}


@Composable
fun ActionButtonIcon(
    label: String,
    contentDescription: String,
    buttonType: ButtonType,
    onClick: () -> Unit,
    icon: Painter,
    iconColor: Color,
    iconSize: Dp,
    space: Dp,
    enabled: Boolean
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        IconLabel(
            label = label,
            contentDescription = contentDescription,
            icon = icon,
            iconColor = iconColor,
            iconSize = iconSize,
            space = space
        )
    }

    when (buttonType) {
        ButtonType.FILLED -> Button(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() },
            enabled = enabled
        )

        ButtonType.OUTLINED -> OutlinedButton(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() },
            enabled = enabled
        )
    }
}

@Composable
fun ActionButtonIcon(
    label: String,
    contentDescription: String,
    buttonType: ButtonType,
    onClick: () -> Unit,
    icon: ImageVector,
    enabled: Boolean
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        IconLabel(
            label = label,
            contentDescription = contentDescription,
            icon = icon,
            iconSize = 40.dp,
            space = 5.dp
        )
    }

    when (buttonType) {
        ButtonType.FILLED -> Button(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() },
            enabled = enabled
        )

        ButtonType.OUTLINED -> OutlinedButton(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() },
            enabled = enabled
        )
    }
}

@Composable
fun ActionButton(
    label: String,
    type: ButtonType, onClick: () -> Unit,
    enabled: Boolean = true
) {
    when (type) {
        ButtonType.FILLED -> {
            Button(
                content = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick() },
                enabled = enabled
            )
        }

        ButtonType.OUTLINED -> {
            OutlinedButton(
                content = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick() },
                enabled = enabled
            )
        }
    }
}

@Preview
@Composable
fun PreviewActionButton() {
    LudyaTheme {
        LudyaSurface {

        }
    }
}

fun testFunction() {
    print("test")
}