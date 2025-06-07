package com.at3nas.ludya.presentation.createCourse

//import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.CourseModule
import com.at3nas.ludya.domain.model.course.Question
import com.at3nas.ludya.presentation.ui.components.ActionButtonIcon
import com.at3nas.ludya.presentation.ui.components.FormInput
import com.at3nas.ludya.presentation.ui.components.IconLabel
import com.at3nas.ludya.presentation.ui.components.Type
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer


@Preview
@Composable
fun CreateCourse(
    //innerPadding: PaddingValues,
) {
    var courseName by rememberSaveable {
        mutableStateOf("")
    }

    var courseDescription by rememberSaveable {
        mutableStateOf("")
    }

    var listOfModules = remember { mutableStateListOf<CourseModule>() }
    listOfModules.add(
        CourseModule(
            moduleNumber = 1
        )
    )

    ColumnContainer(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            //.padding(innerPadding)
            .padding(vertical = 200.dp)
            .padding(horizontal = 25.dp)

    ) {

        Column {
            Text(
                text = stringResource(id = R.string.course),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            FormInput(
                value = courseName,
                label = stringResource(id = R.string.name),
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                    courseName = it
                }
            )

            FormInput(
                value = courseDescription,
                label = stringResource(id = R.string.description),
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                    courseDescription = it
                }
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.module),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOfModules.forEach { module ->
                    ModuleCreationCard(listOfModules, module)
                }
            }
        }




        AddNewElement(
            label = stringResource(id = R.string.add_module),
            onClick = {
                listOfModules.add(
                    CourseModule(
                        moduleNumber = listOfModules.size + 1
                    )
                )
            }
        )
    }
}

@Composable
fun AddNewElement(
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        IconLabel(
            icon = Icons.Filled.Add,
            label = label,
            contentDescription = label,
            iconSize = 25.dp,
            space = 8.dp
        )
    }
}

@Composable
fun QuestionCreationAccordion(
    question: Question,
    listOfQuestions: SnapshotStateList<Question>
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                if (expanded) {
                    Icon(Icons.Filled.KeyboardArrowUp, "")
                } else {
                    Icon(Icons.Filled.KeyboardArrowDown, "")
                }
            }
            Text(stringResource(id = R.string.question) + " ${question.questionNumber}")
        }

        IconButton(
            enabled = true,
            onClick = {
                listOfQuestions.remove(question)
            }
        ) {
            Icon(Icons.Filled.Delete, stringResource(id = R.string.delete_module))
        }
    }

    if (expanded) {
        Column(
            modifier = Modifier.padding(start = 25.dp)
        ) {
            FormInput(
                value = "",
                label = stringResource(id = R.string.question),
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                }
            )

            FormInput(
                value = "",
                label = "Answer 1",
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                }
            )

            FormInput(
                value = "",
                label = "Answer 2",
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                }
            )

            FormInput(
                value = "",
                label = "Answer 3",
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                }
            )

            FormInput(
                value = "",
                label = "Answer 4",
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                }
            )

            FormInput(
                value = "",
                label = "Right answer",
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                }
            )
        }
    }
}

@Composable
fun ModuleCreationCard(
    listOfModules: SnapshotStateList<CourseModule>,
    module: CourseModule
) {
    var moduleName by rememberSaveable {
        mutableStateOf("")
    }

    var listOfQuestions = remember {
        mutableStateListOf<Question>()
    }
    listOfQuestions.add(
        Question(
            questionNumber = 1
        )
    )

    Card(
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // MODULE CARD HEADER //
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.module) + " ${module.moduleNumber}",
                    style = MaterialTheme.typography.bodyLarge
                )

                IconButton(
                    enabled = true,
                    onClick = {
                        listOfModules.remove(module)
                    }
                ) {
                    Icon(Icons.Filled.Delete, stringResource(id = R.string.delete_module))
                }
            }

            FormInput(
                value = moduleName,
                label = stringResource(id = R.string.name),
                leadingIcon = { Icon(Icons.Filled.Info, null) },
                isPassword = false,
                onValueChange = {
                    moduleName = it
                }
            )

            listOfQuestions.forEach { question ->
                QuestionCreationAccordion(question, listOfQuestions)
            }

            AddNewElement(
                label = stringResource(id = R.string.add_question),
                onClick = {
                    listOfQuestions.add(
                        Question(
                            questionNumber = listOfQuestions.size + 1
                        )
                    )
                }
            )
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestDropdownMenu() {
    val category = stringResource(id = R.string.category)

    var expanded by remember {
        mutableStateOf(false)
    }

    var categoryValue by rememberSaveable {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            label = { Text(category) },
            value = categoryValue,
            leadingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(
                        id = R.string.contdesc_dropdownmenu_btn
                    )
                )
            },
            enabled = true,
            readOnly = true,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
            onValueChange = {}
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            CourseCategory.entries.forEach { c ->
                DropdownMenuItem(
                    text = { Text(c.toString()) },
                    onClick = {
                        categoryValue = c.toString()
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryDropdownMenu() {
    var expanded by remember {
        mutableStateOf(false)
    }

    var categoryValue by rememberSaveable {
        mutableStateOf("")
    }

    Box(
    ) {
        FormInput(
            label = stringResource(id = R.string.category),
            value = categoryValue,
            isReadOnly = true,
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Open Menu"
                    )
                }
            },
            onValueChange = {}
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            CourseCategory.entries.forEach { c ->
                DropdownMenuItem(
                    text = { Text(c.toString()) },
                    onClick = {
                        categoryValue = c.toString()
                        expanded = false
                    }
                )
            }
        }

    }
}