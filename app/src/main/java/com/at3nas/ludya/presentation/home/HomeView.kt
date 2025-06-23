package com.at3nas.ludya.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.getCourseCategoryIcon
import com.at3nas.ludya.domain.model.course.localizeCourseCategory
import com.at3nas.ludya.presentation.ui.components.SectionTitle
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

@Preview
@Composable
fun HomeView(
    innerPadding: PaddingValues = PaddingValues(vertical = 200.dp),
//    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val testUsername = "At3nas"

    ColumnContainer(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HomeSearchBar()
        Spacer(Modifier.height(48.dp))

        // Section | Courses //
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionTitle("Explora cursos por categoría")

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CourseCategory.entries.forEach {
                    item(it) {
                        CourseCategoryButton(
                            label = stringResource(localizeCourseCategory(it)),
                            icon = painterResource(getCourseCategoryIcon(it)),
                            onClick = {

                            }
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CourseCategoryButton(
    label: String = stringResource(localizeCourseCategory(CourseCategory.ARTS_HUM)),
    icon: Painter = painterResource(getCourseCategoryIcon(CourseCategory.ARTS_HUM)),
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        onClick = onClick,
        shape = ShapeDefaults.Small
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .background(color = MaterialTheme.colorScheme.primary)
                .size(20.dp)
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val textFieldState = TextFieldState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .heightIn(max = 300.dp)
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = {},
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = { },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = null)
                    },
                    placeholder = {
                        Text(stringResource(id = R.string.search_course))
                    }
                )
            },
            expanded = false,
            onExpandedChange = {},
        ) {
        }
    }
}



@Composable
fun HomePreview() {
    ColumnContainer {
        HomeSearchBar()
    }
}
