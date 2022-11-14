package com.example.fitpal_android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.R

@Composable
fun TopOrderAndSearch(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OrderByDropdown()
        //TODO: Agregar pop-up para busqueda avanzada
        IconButton(
            onClick = { }) {
            Icon(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .size(40.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.advanced_search),
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun OrderByDropdown() {

    val listItems = arrayOf(
        stringResource(R.string.date),
        stringResource(R.string.score),
        stringResource(R.string.difficulty),
        stringResource(R.string.category)
    )

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        // text field
        TextField(
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
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(
                    //TODO: agregar comportamiento de ordenamiento en onClick
                    onClick = {
                        selectedItem = selectedOption
                        expanded = false
                    }) {
                    Text(
                        text = selectedOption
                    )
                }
            }
        }
    }
}