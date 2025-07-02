package com.at3nas.ludya.presentation.ui.components.course

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.getCourseCategoryIcon
import com.at3nas.ludya.domain.model.course.localizeCourseCategory

@Preview
@Composable
fun CourseCategoryIcon(
    containerModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    containerSize: Dp = 20.dp,
    containerPadding: Dp = 2.dp,
    containerShape: Shape = MaterialTheme.shapes.extraSmall,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    courseCategory: CourseCategory = CourseCategory.OTHER
) {
    val categoryIcon: Painter = painterResource(id = getCourseCategoryIcon(courseCategory))
    val categoryName: String = stringResource(localizeCourseCategory(courseCategory))

    Column(
        modifier = Modifier
            .clip(containerShape)
            .background(color = containerColor)
            .size(containerSize)
            .padding(containerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = categoryIcon,
            contentDescription = categoryName,
            tint = iconColor,
            modifier = Modifier.fillMaxSize()
        )
    }
}