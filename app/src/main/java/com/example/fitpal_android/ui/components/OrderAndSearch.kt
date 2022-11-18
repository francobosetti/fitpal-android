package com.example.fitpal_android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.fitpal_android.R
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderByDropdown(
    orderBy : (String, String) -> Unit,
) {

    val mapItems = mapOf(
        stringResource(R.string.date) to "date",
        stringResource(R.string.score) to "score",
        stringResource(R.string.difficulty) to "difficulty",
        stringResource(R.string.category) to "category",
    )

    val listItems = mapItems.keys.toList()

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        // text field
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(R.string.order_by)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        selectedItem = selectedOption
                        orderBy(mapItems.getValue(selectedItem), "desc") // TODO: check direction
                        expanded = false
                    }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = selectedOption
                    )
                }
            }
        }
    }
}