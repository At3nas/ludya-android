package com.at3nas.ludya.presentation.courseView

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val courseRepo: CourseRepository
) :
    ViewModel() {
    private val courseId: String = checkNotNull(savedStateHandle["courseId"])

    var course by mutableStateOf<Course?>(Course())

    init {
        Log.d("COURSE ID: ", courseId)
        loadCourseData()
    }

    private fun loadCourseData() {
        viewModelScope.launch {
            course = courseRepo.getCourseById(courseId)
        }
    }

    fun getCourseName(): String? {
        return course?.courseName
    }

    fun getCourseDescription(): String? {
        return course?.courseDescription
    }

    fun getCourseCategory(): CourseCategory? {
        return course?.courseCategory
    }

    fun getCourseAuthor(): String? {
        return course?.createdBy
    }

    fun getCourseModules(): MutableList<CourseModule>? {
        return course?.courseModules
    }

}