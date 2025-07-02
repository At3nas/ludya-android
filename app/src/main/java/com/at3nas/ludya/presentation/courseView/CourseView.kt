package com.at3nas.ludya.presentation.courseView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ActionButtonIcon
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.CustomTitle
import com.at3nas.ludya.presentation.ui.components.TitleType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.course.CourseCategoryIcon

@Preview
@Composable
fun CourseView(
    courseId: String = "",
    //courseViewModel: CourseViewModel = hiltViewModel(),
    innerPadding: PaddingValues = PaddingValues(vertical = 8.dp)
) {
    val course: Course = Course(
        courseName = "Biología básica",
        courseDescription =
            "Aprende fundamentos de Biología",
        courseCategory = CourseCategory.EXACT_SCI,
        createdBy = "CoolTeacher",
        courseModules = mutableListOf(
            CourseModule(
                moduleName = "Biología celular",
                moduleNumber = 1,
                listOfQuestions = mutableListOf(
                    Question(
                        questionNumber = 1
                    ),
                    Question(
                        questionNumber = 2
                    ),
                    Question(
                        questionNumber = 3
                    )
                )
            ),
            CourseModule(
                moduleName = "Genética",
                moduleNumber = 2,
                listOfQuestions = mutableListOf(
                    Question(
                        questionNumber = 1
                    ),
                    Question(
                        questionNumber = 2
                    )
                )
            )
        )
    )

    ColumnContainer(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
    ) {
        // HEADER //
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // COURSE ICON //
                CourseCategoryIcon(
                    iconColor = MaterialTheme.colorScheme.surfaceDim,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    courseCategory = course.courseCategory,
                    containerSize = 140.dp,
                    containerPadding = 16.dp
                )
                Spacer(Modifier.width(24.dp))
                // TITLE, SUBTITLE AND BUTTON //
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = course.courseName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("${stringResource(id = R.string.created_by)}:")
                            }
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)) {
                                append(" ${course.createdBy}")
                            }
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(24.dp))
                    ActionButton(
                        label = stringResource(id = R.string.enroll),
                        buttonType = ButtonType.FILLED,
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        // ABOUT //
        Column() {
            CustomTitle(
                text = stringResource(id = R.string.about_course),
                titleType = TitleType.SECTION_TITLE
            )
            Text(
                text = course.courseDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.height(16.dp))

        // MODULES //
        CustomTitle(
            text = stringResource(id = R.string.modules),
            titleType = TitleType.SECTION_TITLE
        )
        // Modules
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(course.courseModules) { module ->
                ModuleAccordion(module)
            }
        }
    }
}

@Composable
fun ModuleAccordion(
    module: CourseModule = CourseModule()
) {
    var expanded by rememberSaveable {
        mutableStateOf(true)
    }

    Card {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("${stringResource(R.string.module)} ${module.moduleNumber}:")
                        }
                        append(" ${module.moduleName}")
                    },
                    style = MaterialTheme.typography.bodyLarge
                )

                IconButton(
                    onClick = {
                        expanded = !expanded
                    }
                ) {
                    if (expanded) {
                        Icon(Icons.Filled.KeyboardArrowUp, "")
                    } else {
                        Icon(Icons.Filled.KeyboardArrowDown, "")
                    }
                }
            }

            // QUESTIONS //
            Column(
            ) {
                if (expanded) {
                    module.listOfQuestions.forEach { question ->
                        ActionButtonIcon(
                            label = "${stringResource(R.string.question)} ${question.questionNumber}",
                            contentDescription = "",
                            icon = Icons.Default.PlayArrow,
                            enabled = true,
                            onClick = {},
                            buttonType = ButtonType.OUTLINED,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

//@Composable
//fun ExpandableAccordion() {
//    var expanded by rememberSaveable {
//        mutableStateOf(false)
//    }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        IconButton(
//            onClick = {
//                expanded = !expanded
//            }
//        ) {
//            if (expanded) {
//                Icon(Icons.Filled.KeyboardArrowUp, "")
//            } else {
//                Icon(Icons.Filled.KeyboardArrowDown, "")
//            }
//        }
//        Text(
//            text = "Title",
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//
//    if (expanded) {
//
//
//    }
//}


@Composable
fun CourseTesting() {
    ColumnContainer {
        ModuleAccordion()
    }
}