package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


enum class TitleType {
    SCREEN_TITLE,
    SECTION_TITLE,
    CARD_TITLE,
    DIALOG_TITLE
}

@Composable
fun CustomTitle(
    text: String = "Title",
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign = TextAlign.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    titleType: TitleType = TitleType.SECTION_TITLE,
    bottomMargin: Dp = 8.dp,
    customStyle: TextStyle? = null
) {
    val style = customStyle ?: when (titleType) {
        TitleType.SCREEN_TITLE -> MaterialTheme.typography.headlineMedium
        TitleType.SECTION_TITLE -> MaterialTheme.typography.titleLarge
        TitleType.CARD_TITLE -> MaterialTheme.typography.bodyLarge
        TitleType.DIALOG_TITLE -> MaterialTheme.typography.headlineSmall
    }

    Text(
        text = text,
        style = style,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight
    )
    Spacer(Modifier.height(bottomMargin))
}