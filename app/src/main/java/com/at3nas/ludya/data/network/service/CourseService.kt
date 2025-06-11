package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.at3nas.ludya.domain.model.course.Course
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CourseService @Inject constructor(
    private val firebase: FirebaseClient,
    private val userService: UserService
) {
    private val courseCollection = firebase.db.collection(FirestoreCollections.COLLECTION_COURSE)

    private suspend fun getCourseData(document: String): DocumentSnapshot? {
        return courseCollection
            .document(document)
            .get()
            .await()
    }

    suspend fun addCourse(course: Course) {
        courseCollection
            .document()
            .set(course)
    }

    suspend fun getAllCourses(): QuerySnapshot? {
        return courseCollection
            .get()
            .await()
    }

    suspend fun getCoursesByCurrentUser(): QuerySnapshot? {
        return courseCollection
            .whereEqualTo("createdBy", userService.getUid())
            .get()
            .await()
    }
}