package com.at3nas.ludya.presentation.exploreCourses

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.model.course.stringToCourseCategory
import com.at3nas.ludya.domain.repository.CourseRepository
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreCoursesViewModel @Inject constructor(
    private val courseRepo: CourseRepository

) :
    ViewModel() {

    private val _listOfCourses = MutableLiveData<List<Course>>(emptyList())
    val listOfCourses: LiveData<List<Course>>
        get() = _listOfCourses

    fun loadListOfCourses() {
        viewModelScope.launch {
            _listOfCourses.value = courseRepo.getAllCourses()
        }
    }
}