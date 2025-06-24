package com.at3nas.ludya.presentation.ui.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.LudyaTheme
import com.at3nas.ludya.presentation.ui.components.LudyaSurface


@Composable
fun ColumnContainer(
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    modifier: Modifier = Modifier.padding(horizontal = 16.dp),
    content: @Composable() (ColumnScope.() -> Unit)
) {
    LudyaTheme {
        LudyaSurface {
            Column(
                modifier = modifier,
                horizontalAlignment = horizontalAlignment,
                verticalArrangement = verticalArrangement,
                content = content
            )
        }
    }

}