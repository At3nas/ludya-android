package com.at3nas.ludya.domain.model.course

data class CourseModule (
    val moduleId: String,
    val moduleNumber: Int,
    val name: String,
    val listOfQuestions: MutableList<CourseQuestion>
)