package com.at3nas.ludya.domain.model.userLibrary

import java.util.UUID

data class CourseCollection(
    val courseCollectionId: String = UUID.randomUUID().toString(),
    val collectionName: String = "",
    val collectionDescription: String = "",
    val listOfCourses: MutableList<String> = mutableListOf()
)