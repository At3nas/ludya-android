package com.at3nas.ludya.presentation.createCourse.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.localizeCourseCategory
import com.at3nas.ludya.presentation.createCourse.CreateCourseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCategoryFormSelect(
    createCourseViewModel: CreateCourseViewModel
) {
    // VARIABLES //
    val listOfCategories = CourseCategory.entries

    val menuLabel: String = stringResource(id = R.string.category)

    // STATES //
    var expanded by remember {
        mutableStateOf(false)
    }

    // COMPOSABLES //
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            label = { Text(menuLabel) },
            value = stringResource(localizeCourseCategory(createCourseViewModel.courseCategory)),
            leadingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(
                        id = R.string.contdesc_dropdownmenu_btn
                    )
                )
            },
            enabled = true,
            readOnly = true,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
            onValueChange = {}
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            listOfCategories.forEach { item ->
                DropdownMenuItem(
                    text = { Text(stringResource(localizeCourseCategory(item))) },
                    onClick = {
                        createCourseViewModel.updateCourseCategory(item)
                        expanded = false
                    }
                )
            }
        }
    }
}