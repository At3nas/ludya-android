package com.at3nas.ludya.presentation.createCourse.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.Answer
import com.at3nas.ludya.presentation.createCourse.CreateCourseViewModel
import com.at3nas.ludya.presentation.ui.components.form.FormInput

@Composable
fun AnswerFormInput(
    createCourseViewModel: CreateCourseViewModel,
    moduleId: String,
    questionId: String,
    answer: Answer
) {
    var answerValue by rememberSaveable {
        mutableStateOf("")
    }

    FormInput(
        value = answerValue,
        label = stringResource(id = R.string.answer) + " ${answer.answerNumber}",
        leadingIcon = {
            IconButton (
                onClick = {
                    createCourseViewModel.removeAnswer(moduleId, questionId, answer.answerId)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        onValueChange = {
            answerValue = it
            createCourseViewModel.updateAnswerValue(
                moduleId,
                questionId,
                answer.answerId,
                answerValue
            )
        }
    )
}