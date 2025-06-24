package com.at3nas.ludya.presentation.courseView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.presentation.ui.components.SectionTitle
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.course.CourseCategoryIcon

@Preview
@Composable
fun CourseView(
    // innerPadding = PaddingValues
) {
    // PLACEHOLDER DATA //
    val courseTitle = "Inglés Básico"
    val courseDesc =
        "Aprende vocabulario básico en Inglés que te permitirá mantener conversaciones sencillas."
    val courseCategory = CourseCategory.LANGUAGES
    val courseCreator = "CoolTeacher"

    val createdBy = stringResource(id = R.string.created_by)

    ColumnContainer(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 40.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CourseCategoryIcon(
                    courseCategory = courseCategory,
                    containerSize = 24.dp,
                    iconSize = 16.dp
                )
                Text(
                    text = courseTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = courseDesc,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(24.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("${createdBy}:")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)) {
                        append(" $courseCreator")
                    }
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        // Modules
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            SectionTitle(
                text = stringResource(id = R.string.module)
            )
        }
    }
}
