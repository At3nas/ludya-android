package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import com.at3nas.ludya.presentation.ui.theme.Red700
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp


@Composable
fun SplashHeader(headerIcon: Painter?, headerTitle: String?, headerDesc: String?, iconSize: Dp, modifier: Modifier ) {
    Column(
        content = {
            if (headerIcon != null) {
                Icon(
                    painter = headerIcon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize),
                    tint = Red700
                )
            }

            if (headerTitle != null) {
                Text(text = headerTitle,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Red700,
                    textAlign = TextAlign.Center)
            }

            if (headerDesc != null) {
                Text(text = headerDesc, textAlign = TextAlign.Center)
            }
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    )
}