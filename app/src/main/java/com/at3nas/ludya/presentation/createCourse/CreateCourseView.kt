package com.at3nas.ludya.presentation.createCourse

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.at3nas.ludya.domain.model.course.localizeCourseCategory
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.Type
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
                type = Type.FILLED,
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
            type = Type.FILLED,
            onClick = {
                createCourseViewModel.createCourse()
            }
        )
    }
}

@Composable
fun ModuleCreationCard(
    module: CourseModule,
    createCourseViewModel: CreateCourseViewModel
) {
    val listOfQuestions = remember { mutableStateListOf<Question>() }

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
                        createCourseViewModel.removeModule(module.moduleId)
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

            module.listOfQuestions.forEach { question ->
                QuestionCreationAccordion(
                    createCourseViewModel = createCourseViewModel,
                    moduleId = module.moduleId,
                    question = question,
                    onRemove = {
                        createCourseViewModel.removeQuestion(module.moduleId, question.questionId)
                    }
                )
            }

            AddNewElement(
                label = stringResource(id = R.string.add_question),
                onClick = {
                    listOfQuestions.add(
                        Question(
                            questionNumber = listOfQuestions.size + 1
                        )
                    )
                    createCourseViewModel.addQuestions(module.moduleId, listOfQuestions)
                }
            )
        }
    }
}

@Composable
fun QuestionCreationAccordion(
    createCourseViewModel: CreateCourseViewModel,
    moduleId: String,
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
                        createCourseViewModel.updateQuestionValue(
                            moduleId,
                            question.questionId,
                            questionValue
                        )
                    }
                )

                FormInput(
                    value = answer1,
                    label = stringResource(id = R.string.answer) + " 1",
                    onValueChange = {
                        answer1 = it
                        createCourseViewModel.updateQuestionAnswer1(
                            moduleId,
                            question.questionId,
                            answer1
                        )
                    }
                )

                FormInput(
                    value = answer2,
                    label = stringResource(id = R.string.answer) + " 2",
                    onValueChange = {
                        answer2 = it
                        createCourseViewModel.updateQuestionAnswer2(
                            moduleId,
                            question.questionId,
                            answer2
                        )
                    }
                )

                FormInput(
                    value = answer3,
                    label = stringResource(id = R.string.answer) + " 3",
                    onValueChange = {
                        answer3 = it
                        createCourseViewModel.updateQuestionAnswer3(
                            moduleId,
                            question.questionId,
                            answer3
                        )
                    }
                )

                FormInput(
                    value = answer4,
                    label = stringResource(id = R.string.answer) + " 4",
                    onValueChange = {
                        answer4 = it
                        createCourseViewModel.updateQuestionAnswer4(
                            moduleId,
                            question.questionId,
                            answer4
                        )
                    }
                )

                CorrectAnswerFormSelect(
                    createCourseViewModel,
                    question.questionId,
                    moduleId,
                    listOfAnswers
                )
            }

        }
    }
}

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorrectAnswerFormSelect(
    createCourseViewModel: CreateCourseViewModel,
    questionId: String,
    moduleId: String,
    listOfAnswers: List<String>
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
                    text = { Text(item) },
                    onClick = {
                        createCourseViewModel.updateQuestionCorrectAnswer(
                            moduleId,
                            questionId,
                            item
                        )
                        menuValue = item
                        expanded = false
                    }
                )
            }
        }
    }
}