package com.at3nas.ludya.presentation.createCourse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.presentation.ui.components.CustomTitle
import com.at3nas.ludya.presentation.ui.components.TitleType
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCreatedDialog(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp),
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.height(16.dp))
                CustomTitle(
                    text = stringResource(id = R.string.course_created_dialog_title),
                    titleType = TitleType.DIALOG_TITLE,
                    textAlign = TextAlign.Center,
                    bottomMargin = 16.dp
                )
                Text(
                    text = stringResource(id = R.string.course_created_dialog_subtitle),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                )

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

@Preview
@Composable
fun TestingCourseDialog() {
    ColumnContainer {
        CourseCreatedDialog()
    }
}