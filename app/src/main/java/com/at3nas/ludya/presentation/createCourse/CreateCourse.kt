package com.at3nas.ludya.presentation.createCourse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.at3nas.ludya.presentation.ui.components.LudyaSurface

@Preview
@Composable
fun CreateCourse() {
    LudyaSurface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = "",
                label = { Text("Course name") },
                enabled = true,
                onValueChange = {}
            )

            TextField(
                value = "",
                label = { Text("Course description") },
                enabled = true,
                onValueChange = {}
            )

            
        }


    }
}