package com.at3nas.ludya.domain.repository

import com.at3nas.ludya.domain.model.course.Course
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface CourseRepository {
    suspend fun addCourse(course: Course)
    suspend fun getAllCourses(): List<Course>
    suspend fun getCoursesByCurrentUser(): QuerySnapshot?
    suspend fun getCourseById(courseId: String): Course?
}