package com.at3nas.ludya.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class Achievement (
    val achievementId: String,
    val icon: Painter,
    val name: String,
    val description: String,
    val isObtained: Boolean
)