package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseModule
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CourseService @Inject constructor(
    firebase: FirebaseClient,
    private val userService: UserService
) {
    private val courseCollection = firebase.db.collection(FirestoreCollections.COLLECTION_COURSE)

    private suspend fun getCourseData(document: String): DocumentSnapshot? {
        return courseCollection
            .document(document)
            .get()
            .await()
    }

    fun addCourse(course: Course) {
        courseCollection
            .document(course.courseId)
            .set(course)
    }

    suspend fun getAllCourses(): List<Course> {
        return try {
            courseCollection
                .get()
                .await()
                .toObjects(Course::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getCoursesByCurrentUser(): QuerySnapshot? {
        return courseCollection
            .whereEqualTo("createdBy", userService.getUid())
            .get()
            .await()
    }

    suspend fun getCourseById(courseId: String): Course? {
        return courseCollection
            .document(courseId)
            .get()
            .await()
            .toObject(Course::class.java)
    }
}