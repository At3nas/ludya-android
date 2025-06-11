package com.at3nas.ludya.presentation.ui.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.at3nas.ludya.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSelect(
    listOfItems: List<Any>,
    menuLabel: String
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var itemValue by rememberSaveable {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            label = { Text(menuLabel) },
            value = itemValue,
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
            listOfItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.toString()) },
                    onClick = {
                        itemValue = item.toString()
                        expanded = false
                    }
                )
            }
        }
    }
}