package com.example.fitpal_android.ui.screens.appContent.myRoutines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.TopOrderAndSearch
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun MyRoutines(
    onItemClicked: (Int) -> Unit
) {
    val viewModel = viewModel<MyRoutinesViewModel>(factory = getViewModelFactory() )
    val myRoutinesState = viewModel.myRoutinesState

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
           item {
               TopOrderAndSearch()
           }
            items(myRoutinesState.myRoutines.size) {
                RoutineCard(
                    name = myRoutinesState.myRoutines[it].name,
                    imageUrl = myRoutinesState.myRoutines[it].imageUrl,
                    tags = myRoutinesState.myRoutines[it].tags,
                    rating = myRoutinesState.myRoutines[it].rating,
                    modifier = Modifier.clickable {
                        onItemClicked(myRoutinesState.myRoutines[it].id)
                    }
                )
            }
        }
    }
}