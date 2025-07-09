package com.at3nas.ludya.presentation.quiz.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class QuizAnswer(
    selectedAnswer: Int?,
    isSelected: Boolean,
    isSubmitted: Boolean,
    isCorrect: Boolean?,
) {
    var selectedAnswer by mutableStateOf(selectedAnswer)
    var isSelected by mutableStateOf(isSelected)
    var isSubmitted by mutableStateOf(isSubmitted)
    var isCorrect by mutableStateOf(isCorrect)
}
