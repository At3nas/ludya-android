package com.at3nas.ludya.domain.model.course

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class Question (
    val questionId: String = UUID.randomUUID().toString(),
    val questionNumber: Int = 0,
    val question: String = "",
    val listOfAnswers: MutableList<Answer> = mutableListOf(),
    val correctAnswer: String = "",
    val gainedReward: Reward = Reward(
        gainedExp = 5,
        gainedCoins = 3
    )
)