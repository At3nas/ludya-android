package com.at3nas.ludya.presentation.createCourse

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.course.Answer
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
            val username = userRepository.getUsername()

            val course = username?.let {
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

    // ADD //
    // Module
    fun addNewModule() {
        listOfModules.add(
            CourseModule(
                moduleNumber = listOfModules.size + 1,
                listOfQuestions = mutableStateListOf()
            )
        )
    }

    // Question
    fun addQuestion(moduleId: String, listOfQuestions: MutableList<Question>) {
        val index = getModuleIndex(moduleId)

        if (index != -1) {
            listOfModules[index] = listOfModules[index].copy(
                listOfQuestions = listOfQuestions
            )
        }
    }

    fun addAnswer(moduleId: String, questionId: String, listOfAnswers: MutableList<Answer>) {
        // Indexes //
        val moduleIndex = getModuleIndex(moduleId)
        val questionIndex = getQuestionIndex(moduleId, questionId)

        val listOfQuestions = getModuleQuestions(moduleIndex)

        if (questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                listOfAnswers = listOfAnswers
            )
        }
    }

    // UPDATE //
    // Course
    fun updateCourseName(newName: String) {
        courseName = newName
    }

    fun updateCourseDescription(newDescription: String) {
        courseDescription = newDescription
    }

    fun updateCourseCategory(newCategory: CourseCategory) {
        courseCategory = newCategory
    }

    // Module
    fun updateModuleName(moduleId: String, newName: String) {
        val index = getModuleIndex(moduleId)

        if (index != -1) {
            listOfModules[index] = listOfModules[index].copy(
                moduleName = newName
            )
        }
    }

    // Question
    fun updateQuestionValue(moduleId: String, questionId: String, newQuestionValue: String) {
        val listOfQuestions = getModuleQuestions(getModuleIndex(moduleId))
        val questionIndex = getQuestionIndex(moduleId, questionId)

        if (questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                question = newQuestionValue
            )
        }
    }

    fun updateCorrectAnswer(
        moduleId: String,
        questionId: String,
        newCorrectAnswer: String
    ) {
        val listOfQuestions = getModuleQuestions(getModuleIndex(moduleId))
        val questionIndex = getQuestionIndex(moduleId, questionId)

        if (questionIndex != -1) {
            listOfQuestions[questionIndex] = listOfQuestions[questionIndex].copy(
                correctAnswer = newCorrectAnswer
            )
        }
    }

    // Answer
    fun updateAnswerValue(
        moduleId: String,
        questionId: String,
        answerId: String,
        newAnswerValue: String
    ) {
        val listOfAnswers = getQuestionAnswers(
            getModuleIndex(moduleId),
            getQuestionIndex(moduleId, questionId)
        )
        val answerIndex = getAnswerIndex(moduleId, questionId, answerId)

        if (answerIndex != -1) {
            listOfAnswers[answerIndex] = listOfAnswers[answerIndex].copy(
                answerValue = newAnswerValue
            )
        }
    }

    // REMOVE //
    fun removeModule(moduleId: String) {
        listOfModules.removeAt(getModuleIndex(moduleId))

        listOfModules.forEachIndexed { index, module ->
            if (index != -1) {
                listOfModules[index] = listOfModules[index].copy(
                    moduleNumber = index + 1
                )
            }
        }
    }

    fun removeQuestion(moduleId: String, questionId: String) {
        val listOfQuestions = getModuleQuestions(getModuleIndex(moduleId))
        val questionIndex = getQuestionIndex(moduleId, questionId)

        if (questionIndex != -1) {
            Log.d("REMOVING QUESTION:", "${listOfQuestions[questionIndex]}")
            listOfQuestions.removeAt(questionIndex)

            listOfQuestions.forEachIndexed { index, question ->
                if (index != -1) {
                    listOfQuestions[index] = question.copy(
                        questionNumber = index + 1
                    )
                }
            }
        }
    }

    fun removeAnswer(moduleId: String, questionId: String, answerId: String) {
        // Indexes //
        val moduleIndex = getModuleIndex(moduleId)
        val questionIndex = getQuestionIndex(moduleId, questionId)
        val answerIndex = getAnswerIndex(moduleId, questionId, answerId)

        // Lists //
        val listOfAnswers = getQuestionAnswers(moduleIndex, questionIndex)

        listOfAnswers.removeAt(answerIndex)
        listOfAnswers.forEachIndexed { index, answer ->
            if (index != -1) {
                listOfAnswers[index] = answer.copy(
                    answerNumber = index + 1
                )
            }
        }
    }

    // GET LISTS //
    fun getModuleQuestions(moduleIndex: Int): MutableList<Question> {
        return listOfModules[moduleIndex].listOfQuestions
    }

    private fun getQuestionAnswers(moduleIndex: Int, questionIndex: Int): MutableList<Answer> {
        val listOfQuestions = getModuleQuestions(moduleIndex)
        return listOfQuestions[questionIndex].listOfAnswers
    }

    // GET INDEX //
    private fun getModuleIndex(moduleId: String): Int {
        return listOfModules.indexOfFirst { it.moduleId == moduleId }
    }

    private fun getQuestionIndex(moduleId: String, questionId: String): Int {
        val listOfQuestions = getModuleQuestions(getModuleIndex(moduleId))
        return listOfQuestions.indexOfFirst { it.questionId == questionId }
    }

    private fun getAnswerIndex(moduleId: String, questionId: String, answerId: String): Int {
        val listOfAnswers =
            getQuestionAnswers(getModuleIndex(moduleId), getQuestionIndex(moduleId, questionId))
        return listOfAnswers.indexOfFirst { it.answerId == answerId }
    }
}