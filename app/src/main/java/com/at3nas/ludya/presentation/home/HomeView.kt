package com.at3nas.ludya.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.at3nas.ludya.domain.model.course.CourseCategory
import com.at3nas.ludya.domain.model.course.getCourseCategoryIcon
import com.at3nas.ludya.domain.model.course.localizeCourseCategory
import com.at3nas.ludya.presentation.ui.components.SectionTitle
import com.at3nas.ludya.presentation.ui.components.container.ColumnContainer

//@Preview
@Composable
fun HomeView(
    innerPadding: PaddingValues,
//    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val testUsername = "At3nas"
    ColumnContainer(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .padding(horizontal = 25.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Column(
                modifier = Modifier.padding(25.dp)
            ) {
                Text("¡Bienvenida a Ludya, ${testUsername}!")
            }
        }

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
                        CourseCategoryCard(it)
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCategoryCard(
    category: CourseCategory = CourseCategory.ENGINEERING_TECH,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val categoryIcon: Painter = painterResource(id = getCourseCategoryIcon(category))
    val categoryName: String = stringResource(id = localizeCourseCategory(category))

    val cardBg = MaterialTheme.colorScheme.secondaryContainer
    val cardFg = MaterialTheme.colorScheme.onSecondaryContainer
    val iconBg = MaterialTheme.colorScheme.secondary
    val iconFg = MaterialTheme.colorScheme.onSecondary

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(cardBg)
            .padding(12.dp)
            .width(250.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(iconBg)
                .size(80.dp)
        ) {
            Icon(
                painter = categoryIcon,
                contentDescription = categoryName,
                tint = iconFg,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyMedium,
            color = cardFg,
            textAlign = TextAlign.Center
        )
    }
}
