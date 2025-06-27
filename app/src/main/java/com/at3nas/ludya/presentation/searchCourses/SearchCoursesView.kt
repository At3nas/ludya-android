package com.at3nas.ludya.presentation.searchCourses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.presentation.ui.components.CourseSearchBar
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Preview
@Composable
fun SearchCoursesView(
    innerPadding: PaddingValues = PaddingValues(vertical = 80.dp)
) {
    val listOfCourses = listOf(
        Course(
            courseName = "Curso 1",
            courseDescription = "Aprende en este curso",
            courseCategory = CourseCategory.EXACT_SCI,
            createdBy = "User03934",
            courseModules = mutableListOf(),
        ),
        Course(
            courseName = "Curso 2",
            courseDescription = "Aprende en este curso",
            courseCategory = CourseCategory.EXACT_SCI,
            createdBy = "User8321",
            courseModules = mutableListOf(),
        ),
        Course(
            courseName = "Curso 3",
            courseDescription = "Aprende en este curso",
            courseCategory = CourseCategory.EXACT_SCI,
            createdBy = "User8239",
            courseModules = mutableListOf(),
        )
    )

    ColumnContainer(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
        //.verticalScroll(rememberScrollState())
    ) {
        Text("Search")
        // SEARCH BAR
        CourseSearchBar()

        // COURSES LIST
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            LazyColumn {
                items(listOfCourses) {
                    ListItem(
                        headlineContent = { Text(it.courseName) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}