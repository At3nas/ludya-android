package com.at3nas.ludya.domain.model.userLibrary

import java.util.UUID

data class Library(
    val libraryId: String = UUID.randomUUID().toString(),
    val listOfCourses: MutableList<String> = mutableListOf(),
    val listOfCollections: MutableList<CourseCollection> = mutableListOf()
)