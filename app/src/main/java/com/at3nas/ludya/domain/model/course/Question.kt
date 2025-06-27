package com.at3nas.ludya.domain.model.course

import java.util.UUID

data class Question (
    val questionId: String = UUID.randomUUID().toString(),
    val questionNumber: Int? = null,
    val question: String? = "",
//    val answer1: String? = "",
//    val answer2: String? = "",
//    val answer3: String? = "",
//    val answer4: String? = "",
    val listOfAnswers: MutableList<Answer>,
    val correctAnswer: String? = null,
    val gainedReward: Reward? = Reward(
        gainedXp = 0.2,
        gainedCoins = 3
    )
)