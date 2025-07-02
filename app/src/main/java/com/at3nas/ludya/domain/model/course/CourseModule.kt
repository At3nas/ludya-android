package com.at3nas.ludya.domain.model.course

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class CourseModule(
    val moduleId: String = UUID.randomUUID().toString(),
    val moduleName: String = "",
    val moduleNumber: Int = 1,
    val listOfQuestions: MutableList<Question> = mutableListOf()
)