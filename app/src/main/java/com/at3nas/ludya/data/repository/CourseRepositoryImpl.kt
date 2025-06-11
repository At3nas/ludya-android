package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.service.CourseService
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.repository.CourseRepository
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseService: CourseService
) : CourseRepository {
    override suspend fun addCourse(course: Course) = courseService.addCourse(course)
    override suspend fun getAllCourses(): QuerySnapshot? = courseService.getAllCourses()
    override suspend fun getCoursesByCurrentUser(): QuerySnapshot? = courseService.getCoursesByCurrentUser()
}