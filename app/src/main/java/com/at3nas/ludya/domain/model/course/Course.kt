package com.at3nas.ludya.domain.model.course

data class Course (
    val courseId: String,
    val name: String,
    val description: String,
    val category: CourseCategory,
    val createdBy: String
)