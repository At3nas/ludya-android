package com.at3nas.ludya.presentation.createCourse

import android.util.Log
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

    var courseCategory by mutableStateOf(CourseCategory.OTHER)
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

    // UPDATE | COURSE //
    fun updateCourseName(newName: String) {
        courseName = newName
    }

    fun updateCourseDescription(newDescription: String) {
        courseDescription = newDescription
    }

    fun updateCourseCategory(newCategory: CourseCategory) {
        courseCategory = newCategory
    }

    // UPDATE | MODULE //
    fun updateModuleName(moduleId: String, newName: String) {
        val index = getModuleIndex(moduleId)

        if (index != -1) {
            listOfModules[index] = listOfModules[index].copy(
                moduleName = newName
            )
        }
    }

    // UPDATE | QUESTION //


    // GET | MODULE //
    private fun getModuleIndex(moduleId: String): Int {
        return listOfModules.indexOfFirst { it.moduleId == moduleId }
    }

    // GET | QUESTIONS //

    // ADD //
    fun addNewModule() {
        listOfModules.add(
            CourseModule(
                moduleNumber = listOfModules.size + 1,
                listOfQuestions = mutableStateListOf()
            )
        )
    }

    fun addQuestions(moduleId: String, listOfQuestions: MutableList<Question>) {
        val index = getModuleIndex(moduleId)

        if (index != -1) {
            listOfModules[index] = listOfModules[index].copy(
                listOfQuestions = listOfQuestions
            )
        }
    }

    // REMOVE //
    fun removeModule(moduleId: String) {
        listOfModules.removeAt(getModuleIndex(moduleId))

        listOfModules.forEachIndexed { index, element ->
            if (index != -1) {
                listOfModules[index] = listOfModules[index].copy(
                    moduleNumber = index + 1
                )
            }
        }
    }


    // TESTING FUNCTIONS //
    fun removeQuestion(moduleId: String, questionId: String) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (listOfQuestions != null && questionIndex != null && questionIndex != -1) {
            Log.d("REMOVING QUESTION:", "${listOfQuestions[questionIndex]}")
            listOfQuestions.removeAt(questionIndex)

            listOfQuestions.forEachIndexed { index, question ->
                listOfQuestions[index] = question.copy(
                    questionNumber = index + 1
                )
            }
        }
    }

    fun getModuleQuestions(moduleId: String): MutableList<Question>? {
        val index = getModuleIndex(moduleId)

        return if (index != -1) {
            listOfModules[index].listOfQuestions
        } else {
            null
        }
    }

    fun updateQuestionValue(moduleId: String, questionId: String, newQuestionValue: String) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (questionIndex != null && questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                question = newQuestionValue
            )
        }
    }

    fun updateQuestionAnswer1(moduleId: String, questionId: String, newAnswer1: String) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (questionIndex != null && questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                answer1 = newAnswer1
            )
        }
    }

    fun updateQuestionAnswer2(moduleId: String, questionId: String, newAnswer2: String) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (questionIndex != null && questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                answer2 = newAnswer2
            )
        }
    }

    fun updateQuestionAnswer3(moduleId: String, questionId: String, newAnswer3: String) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (questionIndex != null && questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                answer3 = newAnswer3
            )
        }
    }

    fun updateQuestionAnswer4(moduleId: String, questionId: String, newAnswer4: String) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (questionIndex != null && questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                answer4 = newAnswer4
            )
        }
    }

    fun updateQuestionCorrectAnswer(
        moduleId: String,
        questionId: String,
        newCorrectAnswer: String
    ) {
        val listOfQuestions = getModuleQuestions(moduleId)
        val questionIndex = listOfQuestions?.indexOfFirst { it.questionId == questionId }

        if (questionIndex != null && questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                correctAnswer = newCorrectAnswer
            )
        }
    }
}