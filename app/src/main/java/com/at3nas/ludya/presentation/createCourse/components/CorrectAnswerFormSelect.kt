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
import com.at3nas.ludya.domain.model.course.Answer
import com.at3nas.ludya.presentation.createCourse.CreateCourseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorrectAnswerFormSelect(
    createCourseViewModel: CreateCourseViewModel,
    questionId: String,
    moduleId: String,
    listOfAnswers: MutableList<Answer>
) {
    val menuLabel: String = stringResource(id = R.string.correct_answer)

    var menuValue by remember {
        mutableStateOf("")
    }

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
            value = menuValue,
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
            listOfAnswers.forEach { item ->
                DropdownMenuItem(
                    text = { Text("${item.answerNumber}) ${item.answerValue}") },
                    onClick = {
                        createCourseViewModel.updateCorrectAnswer(
                            moduleId,
                            questionId,
                            item.answerValue
                        )
                        menuValue = item.answerValue
                        expanded = false
                    }
                )
            }
        }
    }
}