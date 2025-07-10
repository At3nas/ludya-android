package com.at3nas.ludya.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ActionButtonIcon
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


@Composable
fun ProfileView(
    innerPadding: PaddingValues,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        profileViewModel.loadProfileData()
    }

    val profile by profileViewModel.profile.collectAsState()
    val username by profileViewModel.username.collectAsState()

    val totalExp = 100

    val color = MaterialTheme.colorScheme.tertiary
    val trackColor = MaterialTheme.colorScheme.tertiaryContainer

    val progress = remember(profile.level.currentExp, totalExp) {
        if (totalExp > 0 && profile.level.currentExp != null) {
            profile.level.currentExp.toFloat() / totalExp
        } else 0f
    }.coerceIn(0f, 1f)

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
                bgColor = MaterialTheme.colorScheme.tertiary,
                userInitial = username[0]
            )
            Text(
                text = profile.displayName,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = "@${username}",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        CurrencyInfo(
            gems = profile.currency.gems.toString(),
            coins = profile.currency.coins.toString()
        )

        Spacer(Modifier.height(32.dp))
        Column {
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
                    text = "${profile.level.level}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "${profile.level.currentExp} / $totalExp XP",
                style = MaterialTheme.typography.bodySmall
            )
        }

//        ActionButton(
//            label = "Cerrar sesión",
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { profileViewModel.logOut() }
//        )
//        ActionButton(
//            label = "Eliminar cuenta",
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { profileViewModel.deleteAccount() }
//        )
    }
}


@Composable
fun ProfileAvatar(
    bgColor: Color,
    userInitial: Char = 'U',
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(bgColor)
            .padding(4.dp)
            .size(80.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.face_0),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.wrapContentSize()
        )
//        Text(
//            text = userInitial.toString(),
//            color = MaterialTheme.colorScheme.onTertiary
//        )
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
