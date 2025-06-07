package com.at3nas.ludya.domain.model.course

data class Course (
    val name: String,
    val description: String,
    val category: CourseCategory,
    val createdBy: String,
    val modules: MutableList<CourseModule>
)