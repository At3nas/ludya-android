package com.at3nas.ludya.domain.model.course.courseProgress

data class QuestionProgress(
    val questionId: String = "",
    var isQuestionCompleted: Boolean = false,
)
