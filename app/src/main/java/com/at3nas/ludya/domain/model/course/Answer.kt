package com.at3nas.ludya.domain.model.course

import java.util.UUID

data class Answer(
    val answerId: String = UUID.randomUUID().toString(),
    val answerNumber: Int,
    val answerValue: String
)