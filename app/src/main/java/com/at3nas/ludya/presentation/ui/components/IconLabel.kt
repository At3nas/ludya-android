package com.at3nas.ludya.presentation.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp


@Composable
fun IconLabel(
    label: String,
    contentDescription: String?,
    icon: Painter,
    iconColor: Color,
    iconSize: Dp,
    space: Dp
) {
    Row(
        content = {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                tint = iconColor,
                modifier = Modifier.size(iconSize)
            )
            Text(label)
        },
        horizontalArrangement = Arrangement.spacedBy(space),
        verticalAlignment = Alignment.CenterVertically
    )
}

@Composable
fun IconLabel(
    label: String,
    contentDescription: String?,
    fontWeight: FontWeight = FontWeight.Normal,
    icon: ImageVector,
    iconSize: Dp,
    space: Dp
) {
    Row(
        content = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(iconSize)
            )
            Text(
                text = label,
                fontWeight = fontWeight
            )
        },
        horizontalArrangement = Arrangement.spacedBy(space),
        verticalAlignment = Alignment.CenterVertically
    )
}

@Preview
@Composable
fun IconLabelPreview() {

}