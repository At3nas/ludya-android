package com.at3nas.ludya.presentation.createCourse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.IconLabel
import com.at3nas.ludya.presentation.ui.components.Type
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.form.FormInput
import com.at3nas.ludya.presentation.ui.components.form.FormSelect


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

        FormSelect(CourseCategory.entries, stringResource(id = R.string.category))

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
            type = Type.FILLED,
            onClick = {
                createCourseViewModel.createCourse()
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCategoryFormSelect(
    listOfItems: List<Any>,
    menuLabel: String
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var itemValue by rememberSaveable {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            label = { Text(menuLabel) },
            value = itemValue,
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
            listOfItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.toString()) },
                    onClick = {
                        itemValue = item.toString()
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun AddNewElement(
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        IconLabel(
            icon = Icons.Filled.Add,
            label = label,
            contentDescription = label,
            iconSize = 25.dp,
            space = 8.dp
        )
    }
}

@Composable
fun QuestionCreationAccordion(
    question: Question,
    onRemove: () -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var questionValue by rememberSaveable {
        mutableStateOf("")
    }

    var answer1 by rememberSaveable {
        mutableStateOf("")
    }

    var answer2 by rememberSaveable {
        mutableStateOf("")
    }

    var answer3 by rememberSaveable {
        mutableStateOf("")
    }

    var answer4 by rememberSaveable {
        mutableStateOf("")
    }

    val listOfAnswers = listOf(answer1, answer2, answer3, answer4)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
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
            Text(
                text = stringResource(id = R.string.question) + " ${question.questionNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        IconButton(
            enabled = true,
            onClick = onRemove
        ) {
            Icon(Icons.Filled.Delete, stringResource(id = R.string.module_delete))
        }
    }

    if (expanded) {
        Column(
            modifier = Modifier.padding(start = 25.dp)
        ) {
            Column {
                FormInput(
                    value = questionValue,
                    label = stringResource(id = R.string.question),
                    placeholder = { Text(stringResource(id = R.string.question_placeholder)) },
                    onValueChange = {
                        questionValue = it
                    }
                )

                FormInput(
                    value = answer1,
                    label = stringResource(id = R.string.answer) + " 1",
                    onValueChange = {
                        answer1 = it
                    }
                )

                FormInput(
                    value = answer2,
                    label = stringResource(id = R.string.answer) + " 2",
                    onValueChange = {
                        answer2 = it
                    }
                )

                FormInput(
                    value = answer3,
                    label = stringResource(id = R.string.answer) + " 3",
                    onValueChange = {
                        answer3 = it
                    }
                )

                FormInput(
                    value = answer4,
                    label = stringResource(id = R.string.answer) + " 4",
                    onValueChange = {
                        answer4 = it
                    }
                )

                FormSelect(
                    listOfItems = listOfAnswers,
                    menuLabel = stringResource(id = R.string.correct_answer)
                )
            }

        }
    }
}

@Composable
fun ModuleCreationCard(
    module: CourseModule,
    createCourseViewModel: CreateCourseViewModel
) {

    var listOfQuestions = remember { mutableStateListOf<Question>() }

    Card(
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // MODULE CARD HEADER //
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.module) + " ${module.moduleNumber}: ${module.moduleName}",
                    style = MaterialTheme.typography.bodyLarge
                )

                IconButton(
                    enabled = true,
                    onClick = {
                        createCourseViewModel.removeModule(module)
                    }
                ) {
                    Icon(Icons.Filled.Delete, stringResource(id = R.string.module_delete))
                }
            }

            FormInput(
                value = module.moduleName,
                label = stringResource(id = R.string.module_name),
                placeholder = { Text(stringResource(id = R.string.module_name_placeholder)) },
                onValueChange = {
                    createCourseViewModel.updateModuleName(module.moduleId, it)
                }
            )

            module.listOfQuestions?.forEach { question ->
                QuestionCreationAccordion(
                    question = question,
                    onRemove = {
                        module.listOfQuestions.remove(question)
                    }
                )
            }

            AddNewElement(
                label = stringResource(id = R.string.add_question),
                onClick = {
                    module.listOfQuestions?.add(
                        Question(
                            questionNumber = listOfQuestions.size + 1
                        )
                    )
                }
            )
        }
    }
}