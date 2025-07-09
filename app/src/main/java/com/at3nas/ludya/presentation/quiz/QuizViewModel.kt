package com.at3nas.ludya.presentation.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.domain.repository.CourseRepository
import com.at3nas.ludya.domain.repository.ProfileRepository
import com.at3nas.ludya.presentation.quiz.model.QuizAnswer
import com.at3nas.ludya.presentation.quiz.model.QuizProgress
import com.at3nas.ludya.presentation.quiz.model.QuizResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val courseRepo: CourseRepository,
    private val profileRepo: ProfileRepository
) : ViewModel() {
    private val courseId: String = checkNotNull(savedStateHandle["courseId"])
    private val moduleId: String = checkNotNull(savedStateHandle["moduleId"])

    // Data
    private var course by mutableStateOf<Course?>(Course())
    var question by mutableStateOf(Question())
    private var listOfQuestions by mutableStateOf(listOf(Question()))

    var quiz = QuizProgress(
        totalQuestions = listOfQuestions.size,
        currentQuestionIndex = 0,
        currentQuestionNumber = 1,
        isFinished = false
    )

    var result by mutableStateOf(
        QuizResult(
            correctAnswers = 0,
            incorrectAnswers = 0,
            gainedCoins = 0,
            gainedExp = 0.0
        )
    )

    var answer by mutableStateOf(
        QuizAnswer(
            selectedAnswer = null,
            isCorrect = false,
            isSelected = false,
            isSubmitted = false
        )
    )

    var progress: Float = 0F

    init {
        loadQuizData()
    }

    // Get data
    private fun loadQuizData() {
        viewModelScope.launch {
            course = courseRepo.getCourseById(courseId)

            val moduleIndex = course?.courseModules?.indexOfFirst { it.moduleId == moduleId } ?: -1

            listOfQuestions = course!!.courseModules[moduleIndex].listOfQuestions.toList()

            quiz.totalQuestions = listOfQuestions.size

            question = listOfQuestions[quiz.currentQuestionIndex]

            updateProgress()
        }
    }

    private fun updateProgress() {
        progress = quiz.currentQuestionNumber.toFloat() / quiz.totalQuestions
    }

    private fun updateQuestion() {
        quiz.currentQuestionIndex += 1
        quiz.currentQuestionNumber += 1
        question = listOfQuestions[quiz.currentQuestionIndex]
        updateProgress()
    }

    private fun resetAnswer() {
        answer.isSelected = false
        answer.isSubmitted = false
        answer.isCorrect = null
        answer.selectedAnswer = null
    }

    fun getQuestion(): String {
        return question.question
    }

    // Validation
    private fun isNotLastQuestion(): Boolean {
        return quiz.currentQuestionNumber < quiz.totalQuestions
    }

    // Answer validation

    // Actions
    fun selectAnswer(index: Int) {
        answer.selectedAnswer = index
        answer.isSelected = true
    }

    fun submitAnswer() {
        answer.isSubmitted = true
        answer.isCorrect =
            question.listOfAnswers[answer.selectedAnswer!!].answerValue == question.correctAnswer

        if (answer.isCorrect == true) {
            result.correctAnswers += 1
            result.gainedExp += question.gainedReward.gainedExp
            result.gainedCoins += question.gainedReward.gainedCoins
        } else {
            result.incorrectAnswers += 1
        }
    }

    fun nextQuestion() {
        if (isNotLastQuestion()) {
            updateQuestion()
            resetAnswer()
        } else {
            quiz.isFinished = true
            addRewards()
        }
    }

    private fun addRewards() {
        viewModelScope.launch {
            // Coins
            val currentCoins = profileRepo.getCoins()
            val newCoins = currentCoins?.plus(result.gainedCoins)

            // Exp
            val currentExp = profileRepo.getExp()
            val newExp = currentExp?.plus(result.gainedExp)

            if (newCoins != null && newExp != null) {
                profileRepo.updateCoins(newCoins)
                profileRepo.updateExp(newExp)
            }
        }
    }
}