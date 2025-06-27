package com.at3nas.ludya.presentation.createCourse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.createCourse.CreateCourseViewModel
import com.at3nas.ludya.presentation.ui.components.form.AddNewElement
import com.at3nas.ludya.presentation.ui.components.form.FormInput

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
                            questionNumber = listOfQuestions.size + 1,
                            listOfAnswers = mutableStateListOf()
                        )
                    )
                    createCourseViewModel.addQuestion(module.moduleId, listOfQuestions)
                }
            )
        }
    }
}