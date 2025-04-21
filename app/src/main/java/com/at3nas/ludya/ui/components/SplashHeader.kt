package com.at3nas.ludya.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import com.at3nas.ludya.ui.theme.Red700
import androidx.compose.ui.Modifier


@Composable
fun SplashHeader(headerIcon: Painter?, headerTitle: String?, headerDesc: String?, modifier: Modifier) {
    Column(
        content = {
            if (headerIcon != null) {
                Icon(
                    painter = headerIcon,
                    contentDescription = null,
                    tint = Red700
                )
            }

            if (headerTitle != null) {
                Text(text = headerTitle)
            }

            if (headerDesc != null) {
                Text(text = headerDesc)
            }
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    )
}