package com.at3nas.ludya.presentation.createCourse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.domain.repository.CourseRepository
import com.at3nas.ludya.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    // VARIABLES / PROPERTIES //
    var courseName by mutableStateOf("")
        private set

    var courseDescription by mutableStateOf("")
        private set

    var courseCategory by mutableStateOf(CourseCategory.NONE)
        private set

    var listOfModules = mutableStateListOf<CourseModule>()
        private set


    // FUNCTIONS //
    fun createCourse() {
        viewModelScope.launch {
            val course = userRepository.getUid()?.let {
                Course(
                    courseName = courseName,
                    courseDescription = courseDescription,
                    courseCategory = courseCategory,
                    createdBy = it,
                    courseModules = listOfModules
                )
            }

            if (course != null) {
                courseRepository.addCourse(course)
            }
        }
    }


    // UPDATE PROPERTIES //
    fun updateCourseName(newName: String) {
        courseName = newName
    }

    fun updateCourseDescription(newDescription: String) {
        courseDescription = newDescription
    }

    fun updateCourseCategory(newCategory: CourseCategory) {
        courseCategory = newCategory
    }

    fun updateModuleName(moduleId: String, newName: String) {
        val index = getModuleIndexById(moduleId)

        if (index != -1) {
            listOfModules[index] = listOfModules[index].copy(
                moduleName = newName
            )
        }
    }

    fun updateModuleNumber(moduleId: String, newModuleNumber: Int) {
        val index = getModuleIndexById(moduleId)

        if (index != -1) {
            listOfModules[index] = listOfModules[index].copy(
                moduleNumber = newModuleNumber
            )
        }
    }

    // GET | MODULE //
    private fun getModuleIndexById(moduleId: String): Int {
        return listOfModules.indexOfFirst { it.moduleId == moduleId }
    }

    fun getModuleName(moduleId: String): String {
        return listOfModules[getModuleIndexById(moduleId)].moduleName
    }

    fun getModuleNumber(moduleId: String): Int {
        return listOfModules[getModuleIndexById(moduleId)].moduleNumber
    }

    fun getModuleListOfQuestions(moduleId: String): MutableList<Question>? {
        return listOfModules[getModuleIndexById(moduleId)].listOfQuestions
    }

    // ADD ELEMENTS TO LISTS //
    fun addNewModule() {
        listOfModules.add(
            CourseModule(
                moduleNumber = listOfModules.size + 1
            )
        )
    }

    fun addNewQuestion(moduleId: String) {
        val listOfQuestions = getModuleListOfQuestions(moduleId)

        listOfQuestions?.add(
            Question(
                questionNumber = listOfQuestions.size + 1
            )
        )
    }

    // REMOVE ELEMENTS FROM LISTS //
    fun removeModule(module: CourseModule) {
        listOfModules.remove(module)

        listOfModules.forEachIndexed { index, element ->
            updateModuleNumber(element.moduleId, index + 1)
        }
    }

    fun removeQuestion(moduleId: String, question: Question) {
        getModuleListOfQuestions(moduleId)?.remove(question)
    }
}