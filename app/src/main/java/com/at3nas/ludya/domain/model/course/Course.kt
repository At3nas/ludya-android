package com.at3nas.ludya.domain.model.course

data class Course (
    val courseName: String,
    val courseDescription: String,
    val courseCategory: CourseCategory,
    val createdBy: String,
    val courseModules: MutableList<CourseModule>
)