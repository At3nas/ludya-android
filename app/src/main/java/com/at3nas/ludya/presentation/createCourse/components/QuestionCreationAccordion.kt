package com.at3nas.ludya.presentation.createCourse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.createCourse.CreateCourseViewModel
import com.at3nas.ludya.presentation.ui.components.form.FormInput

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