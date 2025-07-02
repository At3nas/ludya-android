package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


// TESTING IMPLEMENTATION OF NEW DESIGNS //
@Preview
@Composable
fun TestContainer(
    innerPadding: PaddingValues = PaddingValues(vertical = 200.dp)
) {
    ColumnContainer {

    }
}

@Composable
fun NewButton() {
    val buttonShape: Shape = CutCornerShape(8.dp)
    val colorStops = arrayOf(
        0.0F to Color(0xFFD9DCDE),
        0.85F to Color(0xFF51585B)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Text(
                text = "CLICK ME",
                color = MaterialTheme.colorScheme.onPrimary
            )

        },
        modifier = Modifier
            .clip(buttonShape)
            .size(150.dp, 50.dp)
            .border(
                shape = buttonShape,
                width = 4.dp,
                brush = Brush.radialGradient(
                    colorStops = colorStops,
                    center = Offset.Unspecified,
                    radius = 400F
                )
            )
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                    center = Offset.Unspecified,
                    radius = 900F
                )
            )


    )
}


@Composable
fun QuizScreen(
    innerPadding: PaddingValues,
    question: String,
    options: List<String>,
    correctAnswerIndex: Int,
    onAnswerSelected: (isCorrect: Boolean) -> Unit
) {
    var selectedAnswer by rememberSaveable { mutableStateOf<Int?>(null) }
    var containerColor = MaterialTheme.colorScheme.primary

    ColumnContainer(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTitle(
            text = question,
            titleType = TitleType.SCREEN_TITLE
        )

        options.forEachIndexed { index, option ->
            val isSelected = selectedAnswer == index
            val isCorrect = index == correctAnswerIndex

            Button(
                onClick = {
                    if (selectedAnswer == null) {
                        selectedAnswer = index
                        onAnswerSelected(index == correctAnswerIndex)

                        containerColor = when {
                            selectedAnswer == null -> containerColor
                            isSelected && isCorrect -> Color(0xFF4CAF50)
                            isSelected && !isCorrect -> Color(0xFFF44336)
                            else -> containerColor
                        }
                    }
                },
                modifier = Modifier
                    .size(width = 150.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor,
                    disabledContainerColor = containerColor,
                    contentColor = Color.White
                ),
                enabled = selectedAnswer == null
            ) {
                Text(option)
            }
        }
    }
}
