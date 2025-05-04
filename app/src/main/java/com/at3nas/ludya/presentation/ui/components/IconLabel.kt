package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun IconLabel(label: String, contentDescription: String?, icon: Painter) {
    Row(
        content = {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp)
            )
            Text(label)
        },
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    )
}

@Composable
fun IconLabel(label: String, contentDescription: String?, icon: ImageVector) {
    Row(
        content = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(18.dp)
            )
            Text(label)
        },
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    )
}

@Preview
@Composable
fun IconLabelPreview() {

}