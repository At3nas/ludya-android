package com.at3nas.ludya.presentation.quiz.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt

class QuizResult(
    correctAnswers: Int,
    incorrectAnswers: Int,
    gainedCoins: Int,
    gainedExp: Double
) {
    // PROPERTIES //
    var correctAnswers by mutableIntStateOf(correctAnswers)
    var incorrectAnswers by mutableIntStateOf(incorrectAnswers)
    var gainedCoins by mutableIntStateOf(gainedCoins)
    var gainedExp by mutableDoubleStateOf(gainedExp)


    // FUNCTIONS //
    fun calculateFinalScore(): Int {
        val totalAnswers = correctAnswers + incorrectAnswers

        return ((correctAnswers.toDouble() / totalAnswers) * 100).roundToInt()
    }

    fun isPassed(): Boolean {
        return calculateFinalScore() >= 60
    }
}
