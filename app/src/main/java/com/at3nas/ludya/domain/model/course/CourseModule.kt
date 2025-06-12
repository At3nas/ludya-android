package com.at3nas.ludya.domain.model.course

import java.util.UUID

data class CourseModule(
    val moduleId: String = UUID.randomUUID().toString(),
    val moduleName: String = "",
    val moduleNumber: Int = 1,
    val listOfQuestions: MutableList<Question>
)