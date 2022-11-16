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
import kotlinx.coroutines.Job
import java.util.*

@Composable
fun TopOrderAndSearch(
    orderBy: (String, String) -> Unit,
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OrderByDropdown(orderBy)
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
private fun OrderByDropdown(
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
                    onClick = {
                        selectedItem = selectedOption
                        orderBy(mapItems.getValue(selectedItem), "desc") // TODO: check direction
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