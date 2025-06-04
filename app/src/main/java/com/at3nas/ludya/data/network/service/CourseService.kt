package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import javax.inject.Inject

class CourseService @Inject constructor(
    private val firebase: FirebaseClient
) {
    private suspend fun getCourseData() {

    }

    suspend fun getAllCourses() {

    }
}