package com.at3nas.ludya.presentation.createCourse

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.createCourse.components.CourseCategoryFormSelect
import com.at3nas.ludya.presentation.createCourse.components.ModuleCreationCard
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.form.AddNewElement
import com.at3nas.ludya.presentation.ui.components.form.FormInput


@Composable
fun CreateCourseView(
    innerPadding: PaddingValues,
    createCourseViewModel: CreateCourseViewModel = hiltViewModel()
) {
    ColumnContainer(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 25.dp)
    ) {
        Column {
            // TESTING //
            ActionButton(
                label = "listOfModules",
                type = ButtonType.FILLED,
                onClick = {
                    Log.d("listOfModules: ", "${createCourseViewModel.listOfModules}")
                }
            )
            // END TESTING //

            Text(
                text = stringResource(id = R.string.course),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            FormInput(
                value = createCourseViewModel.courseName,
                label = stringResource(id = R.string.course_name),
                placeholder = { Text(stringResource(id = R.string.course_name_placeholder)) },
                onValueChange = {
                    createCourseViewModel.updateCourseName(it)
                }
            )

            FormInput(
                value = createCourseViewModel.courseDescription,
                label = stringResource(id = R.string.course_description),
                placeholder = { Text(stringResource(id = R.string.course_description_placeholder)) },
                onValueChange = {
                    createCourseViewModel.updateCourseDescription(it)
                }
            )
        }

        CourseCategoryFormSelect(createCourseViewModel)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.module),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                createCourseViewModel.listOfModules.forEach { module ->
                    ModuleCreationCard(module, createCourseViewModel)
                }
            }
        }

        AddNewElement(
            label = stringResource(id = R.string.module_add),
            onClick = {
                createCourseViewModel.addNewModule()
            }
        )

        ActionButton(
            label = stringResource(id = R.string.create),
            type = ButtonType.FILLED,
            onClick = {
                createCourseViewModel.createCourse()
            }
        )
    }
}
