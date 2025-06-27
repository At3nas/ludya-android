package com.at3nas.ludya.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.Achievement
import com.at3nas.ludya.presentation.profile.components.CurrencyInfo
import com.at3nas.ludya.presentation.profile.components.LevelInfo
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


@Composable
fun ProfileView(
    innerPadding: PaddingValues,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val displayName = profileViewModel.displayName
    val username = profileViewModel.username
    val coins = profileViewModel.coins
    val gems = profileViewModel.gems
    val level = profileViewModel.level
    val exp = profileViewModel.exp

    ColumnContainer(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 25.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ProfileAvatar(
                MaterialTheme.colorScheme.tertiary,
                painterResource(id = R.drawable.face_female_1)
            )
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

        CurrencyInfo(
            gems = gems.toString(),
            coins = coins.toString()
        )

        if (level != null && exp != null) {
            LevelInfo(
                level = 50,
                exp = 20
            )
        }

//        ProfileSection(stringResource(id = R.string.achievements))

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
fun ProfileSection(sectionTitle: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = sectionTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "View all",
                fontSize = 16.sp
            )
        }

        Card(
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    AchievementItem(
                        Achievement(
                            achievementId = "1",
                            icon = painterResource(id = R.drawable.icon_achievement),
                            name = "First lesson",
                            description = "Complete your first lesson",
                            isObtained = false
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}

@Composable
fun AchievementItem(achievement: Achievement) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Icon(
            painter = achievement.icon,
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = achievement.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = achievement.description,
            fontSize = 10.sp,
        )
    }
}
