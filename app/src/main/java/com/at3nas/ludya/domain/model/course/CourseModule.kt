package com.at3nas.ludya.domain.model.course

data class CourseModule (
    val moduleNumber: Int? = null,
    val name: String? = "",
    val listOfQuestions: MutableList<Question>? = null
)