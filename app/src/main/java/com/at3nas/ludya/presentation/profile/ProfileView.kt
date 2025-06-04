package com.at3nas.ludya.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.ui.Blue700
import com.at3nas.ludya.presentation.ui.Green700
import com.at3nas.ludya.presentation.ui.LudyaTheme
import com.at3nas.ludya.presentation.ui.Yellow700
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.IconLabel
import com.at3nas.ludya.presentation.ui.components.LudyaSurface
import com.at3nas.ludya.presentation.ui.components.Type


@Preview
@Composable
fun ProfileView(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val displayName = profileViewModel.displayName
    val username = profileViewModel.username
    val coins = profileViewModel.coins
    val gems = profileViewModel.gems

    LudyaTheme {
        LudyaSurface {
            Column(
                modifier = Modifier.padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ProfileAvatar(Green700, painterResource(id = R.drawable.face_female_1))
                        Text(
                            text = "$displayName",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                        Text(
                            text = "@$username",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )


                    }

                    Row(
                    ) {
                        CurrencyInfo(
                            label = coins.toString(),
                            contentDesc = "Coins",
                            icon = painterResource(id = R.drawable.icon_coin),
                            iconColor = Yellow700
                        )
                        Spacer(Modifier.size(20.dp))
                        CurrencyInfo(
                            label = gems.toString(),
                            contentDesc = "Gems",
                            icon = painterResource(id = R.drawable.icon_gem),
                            iconColor = Blue700
                        )
                    }

                    Column {
                        ActionButton(
                            type = Type.FILLED,
                            label = "+1 gem",
                            enabled = true,
                            contentDescription = "add coins",
                            onClick = {
                                profileViewModel.updateDisplayName()
                            }
                        )
                    }
                }
            )
        }
    }
}


@Composable
fun ProfileAvatar(bgColor: Color, avatar: Painter) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(bgColor)
            .padding(4.dp)
            .size(100.dp)
    ) {
        Image(
            painter = avatar,
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun CurrencyInfo(label: String, contentDesc: String, icon: Painter, iconColor: Color) {
    IconLabel(
        label = label,
        icon = icon,
        contentDescription = contentDesc,
        iconColor = iconColor,
        iconSize = 20.dp,
        space = 5.dp
    )
}