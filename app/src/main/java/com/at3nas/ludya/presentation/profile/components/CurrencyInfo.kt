package com.at3nas.ludya.presentation.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.ui.components.IconLabel
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Composable
fun CurrencyInfo(
    coins: String = "150",
    gems: String = "25"
) {
    //val iconColor: Color = MaterialTheme.colorScheme.primary
    val iconSize: Dp = 20.dp
    val iconLabelGap: Dp = 5.dp

    Row(
    ) {
        IconLabel(
            label = coins,
            contentDescription = "Coins",
            icon = painterResource(id = R.drawable.icon_coin),
            iconColor = Color(0xFFdfb935),
            iconSize = iconSize,
            space = iconLabelGap
        )
        Spacer(Modifier.size(20.dp))
        IconLabel(
            label = gems,
            contentDescription = "Gems",
            icon = painterResource(id = R.drawable.icon_gem),
            iconColor = Color(0xFF3ab1e1),
            iconSize = iconSize,
            space = iconLabelGap
        )
    }
}


@Preview
@Composable
fun CurrencyInfoPreview() {
    ColumnContainer {
        CurrencyInfo()
    }
}