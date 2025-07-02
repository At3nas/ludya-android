package com.at3nas.ludya.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Composable
fun ProfileAvatar(
    modifier: Modifier = Modifier,
    drawableResource: Int = R.drawable.face_0,
    contentDescription: String = ""
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.tertiary),
        painter = painterResource(id = drawableResource),
        contentDescription = contentDescription,
    )
}


@Preview
@Composable
fun ProfileAvatarPreview() {
    ColumnContainer {
        ProfileAvatar()
    }
}