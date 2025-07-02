package com.at3nas.ludya.domain.model.course

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class Answer(
    val answerId: String = UUID.randomUUID().toString(),
    val answerNumber: Int = 0,
    val answerValue: String = ""
)