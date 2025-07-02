package com.at3nas.ludya.presentation.quizView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.domain.model.course.Answer
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.CustomTitle
import com.at3nas.ludya.presentation.ui.components.TitleType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Preview
@Composable
fun QuizView(
    moduleId: String = "0",
    onAnswerSelected: (isCorrect: Boolean) -> Unit = {}
) {
    val listOfQuestions = listOf(
        Question(
            questionNumber = 1,
            question = "Organismo de dos o más células",
            listOfAnswers = mutableListOf(
                Answer(answerNumber = 1, answerValue = "Monocelular"),
                Answer(answerNumber = 2, answerValue = "Bicelular"),
                Answer(answerNumber = 3, answerValue = "Pluricelular"),
                Answer(answerNumber = 4, answerValue = "Multicelular")
            ),
            correctAnswer = "Pluricelular"
        ),
        Question(questionNumber = 2),
        Question(questionNumber = 3),
        Question(questionNumber = 4),
        Question(questionNumber = 5)
    )

    val question = listOfQuestions[0]
    val currentQuestion = question.questionNumber

    var selectedAnswer by rememberSaveable { mutableStateOf<Int?>(null) }
    var containerColor = MaterialTheme.colorScheme.primary

    ColumnContainer {
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
                progress = { currentQuestion.toFloat() / listOfQuestions.size },
                color = MaterialTheme.colorScheme.tertiary,
                trackColor = MaterialTheme.colorScheme.surfaceDim,
                strokeCap = StrokeCap.Round,
                gapSize = (-15).dp,
                drawStopIndicator = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
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
        question.listOfAnswers.forEachIndexed { index, answer ->
            val isSelected = selectedAnswer == index
            val isCorrect = answer.answerValue == question.correctAnswer

            ActionButton(
                label = answer.answerValue,
                buttonType = ButtonType.OUTLINED,
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = selectedAnswer == null,
                onClick = {
                    if (selectedAnswer == null) {
                        selectedAnswer = index
                        onAnswerSelected(isCorrect)

                        containerColor = when {
                            selectedAnswer == null -> containerColor
                            isSelected && isCorrect -> Color(0xFF4CAF50)
                            isSelected && !isCorrect -> Color(0xFFF44336)
                            else -> containerColor
                        }
                    }
                }
            )
            Spacer(Modifier.height(8.dp))
        }

    }
}