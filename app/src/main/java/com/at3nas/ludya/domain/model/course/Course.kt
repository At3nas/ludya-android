package com.at3nas.ludya.domain.model.course

import androidx.annotation.Keep
import androidx.compose.runtime.MutableState
import java.util.UUID

@Keep
data class Course (
    val courseId: String = UUID.randomUUID().toString(),
    val courseName: String = "",
    val courseDescription: String = "",
    val courseCategory: CourseCategory = CourseCategory.OTHER,
    val createdBy: String = "",
    val courseModules: MutableList<CourseModule> = mutableListOf()
)