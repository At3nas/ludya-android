package com.at3nas.ludya.domain.model.course.courseProgress

data class ModuleProgress(
    val moduleId: String = "",
    val questionsProgress: MutableList<QuestionProgress> = mutableListOf(),
    var isModuleCompleted: Boolean = false
)
