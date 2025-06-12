package com.at3nas.ludya.presentation.ui.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.components.IconLabel

@Composable
fun AddNewElement(
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        IconLabel(
            icon = Icons.Filled.Add,
            label = label,
            contentDescription = label,
            iconSize = 25.dp,
            space = 8.dp
        )
    }
}