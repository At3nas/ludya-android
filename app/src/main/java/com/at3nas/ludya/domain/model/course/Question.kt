package com.at3nas.ludya.domain.model.course

data class Question (
    val questionNumber: Int? = null,
    val question: String? = "",
    val answer1: String? = "",
    val answer2: String? = "",
    val answer3: String? = "",
    val answer4: String? = "",
    val rightAnswer: String? = ""
)