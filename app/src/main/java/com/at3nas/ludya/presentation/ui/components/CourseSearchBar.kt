package com.at3nas.ludya.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import com.at3nas.ludya.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseSearchBar(
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {}
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