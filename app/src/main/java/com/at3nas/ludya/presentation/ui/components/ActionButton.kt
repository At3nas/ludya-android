package com.at3nas.ludya.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.ui.theme.LudyaTheme

enum class Type {
    FILLED,
    OUTLINED
}

@Composable
fun ActionButtonIcon(
    label: String,
    contentDescription: String,
    type: Type,
    onClick: () -> Unit,
    icon: Painter
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        IconLabel(
            label = label,
            contentDescription = contentDescription,
            icon = icon
        )
    }

    when (type) {
        Type.FILLED -> Button(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() })

        Type.OUTLINED -> OutlinedButton(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() })
    }
}

@Composable
fun ActionButtonIcon(
    label: String,
    contentDescription: String,
    type: Type,
    onClick: () -> Unit,
    icon: ImageVector
) {
    val content: @Composable() (RowScope.() -> Unit) = {
        IconLabel(
            label = label,
            contentDescription = contentDescription,
            icon = icon
        )
    }

    when (type) {
        Type.FILLED -> Button(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() })

        Type.OUTLINED -> OutlinedButton(
            content = content,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick() })
    }
}

@Composable
fun ActionButton(
    label: String, contentDescription: String, type: Type, onClick: () -> Unit
) {
    when (type) {
        Type.FILLED -> {
            Button(
                content = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick() },
            )
        }

        Type.OUTLINED -> {
            OutlinedButton(
                content = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick() },
            )
        }
    }
}

@Preview
@Composable
fun PreviewActionButton() {
    LudyaTheme {
        LudyaSurface {
            Column {
                ActionButton(
                    label = "Click me",
                    contentDescription = "contenido",
                    onClick = { testFunction() },
                    type = Type.FILLED
                )
                ActionButton(
                    label = "Click me",
                    contentDescription = "contenido",
                    onClick = { testFunction() },
                    type = Type.OUTLINED
                )
                ActionButtonIcon(
                    label = "Like",
                    contentDescription = "contenido",
                    icon = Icons.Filled.Favorite,
                    onClick = { testFunction() },
                    type = Type.FILLED
                )
            }
        }
    }
}

fun testFunction() {
    print("test")
}