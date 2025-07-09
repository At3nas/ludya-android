package com.at3nas.ludya.presentation.quiz.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class QuizProgress(
    totalQuestions: Int,
    currentQuestionIndex: Int,
    currentQuestionNumber: Int,
    isFinished: Boolean
) {
    var totalQuestions by mutableIntStateOf(totalQuestions)
    var currentQuestionNumber by mutableIntStateOf(currentQuestionNumber)
    var currentQuestionIndex by mutableIntStateOf(currentQuestionIndex)
    var isFinished by mutableStateOf(isFinished)
}