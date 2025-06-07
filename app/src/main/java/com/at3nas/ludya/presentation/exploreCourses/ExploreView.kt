package com.at3nas.ludya.presentation.exploreCourses

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Composable
fun ExploreView(
    innerPadding: PaddingValues,
) {
    ColumnContainer(
        modifier = Modifier.padding(innerPadding)
    ) {
        Text("Explore View")
    }
}