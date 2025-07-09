package com.at3nas.ludya.domain.model.course.courseProgress

data class CourseProgress(
    val courseId: String = "",
    val modulesProgress: MutableList<ModuleProgress> = mutableListOf(),
    var isCourseCompleted: Boolean = false
)
