package com.at3nas.ludya.presentation.exploreCourses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.localizeCourseCategory
import com.at3nas.ludya.presentation.ui.components.CustomTitle
import com.at3nas.ludya.presentation.ui.components.TitleType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.course.CourseCategoryIcon

//@Preview
@Composable
fun ExploreCoursesView(
    navigateToCourseView: (courseId: String) -> Unit = {},
    navigateToQuizView: (courseId: String, moduleId: String) -> Unit = { _, _ -> },
    innerPadding: PaddingValues = PaddingValues(vertical = 200.dp),
    exploreCoursesViewModel: ExploreCoursesViewModel = hiltViewModel()
) {
    val listOfCourses = exploreCoursesViewModel.listOfCourses.observeAsState().value

    LaunchedEffect(Unit) {
        exploreCoursesViewModel.loadListOfCourses()
    }

    ColumnContainer(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(innerPadding)
    ) {
        CustomTitle(
            text = "Todos los cursos",
            titleType = TitleType.SCREEN_TITLE
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOfCourses?.let {
                items(it) { course ->
                    CourseCard(course = course, onClick = {
                        navigateToCourseView(course.courseId)
                    })
                }
            }

        }
    }
}


@Composable
fun CourseCard(course: Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = course.courseName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = course.courseDescription,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                AssistChip(
                    onClick = {},
                    label = { Text(text = stringResource(localizeCourseCategory(course.courseCategory))) },
                    leadingIcon = {
                        CourseCategoryIcon(
                            courseCategory = course.courseCategory
                        )
                    }
                )
            }
        }
    }
}
