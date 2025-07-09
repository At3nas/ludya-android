package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.at3nas.ludya.domain.model.course.courseProgress.UserCourseProgress
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseProgressService @Inject constructor(
    private val firebase: FirebaseClient
) {
    private val courseProgressCollection =
        firebase.db.collection(FirestoreCollections.COLLECTION_COURSE_PROGRESS)

    fun addCourseProgress(userCourseProgress: UserCourseProgress) {
        val uid = firebase.auth.currentUser?.uid

        if (uid != null) {
            courseProgressCollection
                .document(uid)
                .set(userCourseProgress)
        }
    }
}