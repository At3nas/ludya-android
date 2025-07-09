package com.at3nas.ludya.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.quiz.components.FinalScoreDialog
import com.at3nas.ludya.presentation.ui.components.ActionButton
import com.at3nas.ludya.presentation.ui.components.ButtonType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.correctAnswer
import com.at3nas.ludya.presentation.ui.incorrectAnswer


@Composable
fun QuizView(
    courseId: String = "",
    moduleId: String = "",
    navigateBack: () -> Unit = {},
    quizViewModel: QuizViewModel = hiltViewModel(),
) {
    ColumnContainer(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 200.dp)
    ) {
        if (quizViewModel.quiz.isFinished) {
            FinalScoreDialog(
                result = quizViewModel.result,
                onConfirm = {
                    quizViewModel.quiz.isFinished = false
                    navigateBack.invoke()
                }
            )
        }


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
                progress = { quizViewModel.progress },
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
                    text = quizViewModel.getQuestion(),
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
            quizViewModel.question.listOfAnswers.forEachIndexed { index, answer ->
                val isSelected = quizViewModel.answer.selectedAnswer == index

                OutlinedButton(
                    content = { Text(answer.answerValue) },
                    enabled = !quizViewModel.answer.isSubmitted,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) {
                            MaterialTheme.colorScheme.surfaceContainer
                        } else {
                            Color.Unspecified
                        },
                        disabledContainerColor = when {
                            isSelected && quizViewModel.answer.isCorrect == true -> correctAnswer
                            isSelected && quizViewModel.answer.isCorrect == false -> incorrectAnswer
                            else -> Color.Unspecified
                        },
                    ),
                    onClick = {
                        quizViewModel.selectAnswer(index)
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
                enabled = quizViewModel.answer.isSelected,
                onClick = {
                    quizViewModel.submitAnswer()
                }
            )
            Spacer(Modifier.width(8.dp))
            ActionButton(
                label = stringResource(id = R.string.next),
                buttonType = ButtonType.OUTLINED,
                enabled = quizViewModel.answer.isSubmitted,
                onClick = {
                    quizViewModel.nextQuestion()
                }
            )
        }
    }
}