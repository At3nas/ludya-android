package com.at3nas.ludya.domain.model


//enum class UserRole() {
//    STUDENT,
//    TEACHER
//}

data class User(
    val uuid: String? = null,
    val email: String? = null,
    val username: String? = null,
    val isTeacher: Boolean = false
)