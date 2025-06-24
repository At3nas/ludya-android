package com.at3nas.ludya.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.R
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.getCourseCategoryIcon
import com.at3nas.ludya.domain.model.course.localizeCourseCategory
import com.at3nas.ludya.presentation.ui.components.SectionTitle
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer
import com.at3nas.ludya.presentation.ui.components.course.CourseCategoryIcon

@Preview
@Composable
fun HomeView(
    innerPadding: PaddingValues = PaddingValues(vertical = 200.dp),
//    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val testUsername = "At3nas"
    val homeWelcome = stringResource(id = R.string.home_welcome)
    val homeWelcomeSubtitle = stringResource(id = R.string.home_welcome_subtitle)

    ColumnContainer(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Section | Header //
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            Text(
                text = buildAnnotatedString {
                    append(homeWelcome)
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" $testUsername")
                    }
                },
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = homeWelcomeSubtitle,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
            // Search bar
            HomeSearchBar()
            Spacer(Modifier.height(48.dp))
        }

        // Section | Search by category //
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionTitle("Explora cursos por categoría")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.requiredWidth(LocalConfiguration.current.screenWidthDp.dp)
            ) {
                CourseCategory.entries.forEach {
                    item(it) {
                        CourseCategoryButton(
                            courseCategory = it,
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
    courseCategory: CourseCategory = CourseCategory.OTHER,
    onClick: () -> Unit = {}
) {
    val label: String = stringResource(localizeCourseCategory(courseCategory))

    OutlinedButton(
        onClick = onClick,
        shape = ShapeDefaults.Small
    ) {
        CourseCategoryIcon(
            courseCategory = courseCategory
        )
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
            .semantics { isTraversalGroup = true }
        //.heightIn(max = 300.dp)
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
