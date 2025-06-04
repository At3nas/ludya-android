package com.at3nas.ludya.domain.repository

interface CourseRepository {
    suspend fun getCourse(): String
    suspend fun createCourse(): String
}