package com.at3nas.ludya.presentation.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


@Composable
fun LevelInfo(
    level: Int = 10,
    exp: Int = 16,
    totalExp: Int = 100
) {
    val color = MaterialTheme.colorScheme.tertiary
    val trackColor = MaterialTheme.colorScheme.tertiaryContainer

    val progress = remember(exp, totalExp) {
        if (totalExp > 0) {
            exp.toFloat() / totalExp
        } else 0f
    }.coerceIn(0f, 1f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(80.dp)
    ) {
        CircularProgressIndicator(
            progress = { progress },
            strokeWidth = 8.dp,
            color = color,
            trackColor = trackColor,
            gapSize = 0.dp,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "$level",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(8.dp))
    Text("$exp / $totalExp XP", style = MaterialTheme.typography.bodySmall)
}

@Preview
@Composable
fun LevelInfoPreview() {
    ColumnContainer {
        LevelInfo()
    }
}