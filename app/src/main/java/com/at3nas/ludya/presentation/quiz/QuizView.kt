package com.at3nas.ludya.presentation.quizView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.Answer
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.correctAnswer
import com.at3nas.ludya.presentation.ui.incorrectAnswer

@Preview
@Composable
fun QuizView(
    innerPadding: PaddingValues = PaddingValues(vertical = 200.dp),
    moduleId: String = "0",
    onAnswerSelected: (isCorrect: Boolean) -> Unit = {}
) {
    val listOfQuestions = listOf(
        Question(
            questionNumber = 1,
            question = "Organismo de dos o más células",
            listOfAnswers = mutableListOf(
                Answer(answerNumber = 1, answerValue = "Extracelular"),
                Answer(answerNumber = 2, answerValue = "Bicelular"),
                Answer(answerNumber = 3, answerValue = "Pluricelular"),
                Answer(answerNumber = 4, answerValue = "Multicelular")
            ),
            correctAnswer = "Pluricelular"
        ),
        Question(
            questionNumber = 2,
            question = "Organismo de una célula",
            listOfAnswers = mutableListOf(
                Answer(answerNumber = 1, answerValue = "Monocelular"),
                Answer(answerNumber = 2, answerValue = "Unocelular"),
                Answer(answerNumber = 3, answerValue = "Bicelular"),
                Answer(answerNumber = 4, answerValue = "Unicelular")
            ),
            correctAnswer = "Unicelular"
        ),
        Question(questionNumber = 3),
        Question(questionNumber = 4),
        Question(questionNumber = 5)
    )

    var isAnswerSelected by rememberSaveable { mutableStateOf(false) }
    var isAnswerSubmited by rememberSaveable { mutableStateOf(false) }
    var isAnswerCorrect by rememberSaveable { mutableStateOf<Boolean?>(null) }
    var selectedAnswer by rememberSaveable { mutableStateOf<Int?>(null) }

    var currentQuestionIndex by rememberSaveable { mutableIntStateOf(0) }
    val question = listOfQuestions[currentQuestionIndex]


    ColumnContainer(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(innerPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Icon(Icons.Default.Close, "")
            }
            LinearProgressIndicator(
                progress = { question.questionNumber.toFloat() / listOfQuestions.size },
                color = MaterialTheme.colorScheme.tertiaryContainer,
                trackColor = MaterialTheme.colorScheme.surfaceDim,
                strokeCap = StrokeCap.Round,
                gapSize = (-15).dp,
                drawStopIndicator = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
        }
        Spacer(Modifier.height(32.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = question.question,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            question.listOfAnswers.forEachIndexed { index, answer ->
                val isSelected = selectedAnswer == index

                OutlinedButton(
                    content = { Text(answer.answerValue) },
                    enabled = !isAnswerSubmited,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) {
                            MaterialTheme.colorScheme.surfaceContainer
                        } else {
                            Color.Unspecified
                        },
                        disabledContainerColor = when {
                            isSelected && isAnswerCorrect == true -> correctAnswer
                            isSelected && isAnswerCorrect == false -> incorrectAnswer
                            else -> Color.Unspecified
                        },
                    ),
                    onClick = {
                        selectedAnswer = index
                        isAnswerSelected = true
                    },
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            ActionButton(
                label = stringResource(id = R.string.submit),
                enabled = isAnswerSelected,
                onClick = {
                    isAnswerSubmited = true
                    isAnswerCorrect =
                        question.listOfAnswers[selectedAnswer!!].answerValue == question.correctAnswer
                }
            )
            Spacer(Modifier.width(8.dp))
            ActionButton(
                label = "Next",
                buttonType = ButtonType.OUTLINED,
                enabled = isAnswerSubmited,
                onClick = {
                    isAnswerSelected = false
                    isAnswerSubmited = false
                    isAnswerCorrect = null
                    selectedAnswer = null
                    currentQuestionIndex += 1
                }
            )
        }
    }
}