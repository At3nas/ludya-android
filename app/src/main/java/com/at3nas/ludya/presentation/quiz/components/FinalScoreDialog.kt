package com.at3nas.ludya.presentation.quiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.quiz.model.QuizResult
import com.at3nas.ludya.presentation.ui.components.CustomTitle
import com.at3nas.ludya.presentation.ui.components.IconLabel
import com.at3nas.ludya.presentation.ui.components.TitleType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalScoreDialog(
    result: QuizResult = QuizResult(
        correctAnswers = 1,
        incorrectAnswers = 3,
        gainedExp = 0.0,
        gainedCoins = 0
    ),
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                if (result.isPassed()) {
                    FinalScoreDialogHeader(
                        title = stringResource(id = R.string.good_job),
                        subtitle = stringResource(id = R.string.quiz_passed)
                    )
                } else {
                    FinalScoreDialogHeader(
                        title = stringResource(id = R.string.try_again),
                        subtitle = stringResource(id = R.string.can_improve)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    SingleResultRow(
                        label = stringResource(id = R.string.correct_answers),
                        result = "${result.correctAnswers}",
                        icon = Icons.Default.Check
                    )
                    SingleResultRow(
                        label = stringResource(id = R.string.incorrect_answers),
                        result = "${result.incorrectAnswers}",
                        icon = Icons.Default.Close
                    )
                    SingleResultRow(
                        label = stringResource(id = R.string.final_score),
                        icon = Icons.Default.Star,
                        result = "${result.calculateFinalScore()}%"
                    )
                }
                Spacer(Modifier.height(16.dp))
                if (result.isPassed()) {
                    Text(
                        text = "${stringResource(id = R.string.reward)}:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text("${result.gainedCoins} ${stringResource(id = R.string.coins)}")
                    Text("${result.gainedExp} ${stringResource(id = R.string.exp)}")
                } else {
                    Text(
                        text = stringResource(id = R.string.needed_score),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onConfirm
                    ) {
                        Text(stringResource(id = R.string.confirm))
                    }
                }
            }
        }
    }
}

@Composable
fun SingleResultRow(
    label: String = "",
    result: String = "",
    icon: ImageVector = Icons.Default.Info
) {
    Row {
        IconLabel(
            label = "${label}: ",
            contentDescription = label,
            fontWeight = FontWeight.Bold,
            space = 8.dp,
            iconSize = 20.dp,
            icon = icon,
        )
        Text(result)
    }
}

@Composable
fun FinalScoreDialogHeader(
    title: String = "",
    subtitle: String = ""
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTitle(
            text = title,
            titleType = TitleType.DIALOG_TITLE,
            bottomMargin = 8.dp
        )
        Text(
            text = subtitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
    }
    Spacer(Modifier.height(16.dp))
}


@Preview
@Composable
fun TestDialog() {
    ColumnContainer {
        FinalScoreDialog()
    }
}