package com.example.fitpal_android.ui.screens.appContent.favRoutines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
fun FavRoutines(
    onItemClicked: (Int) -> Unit,
    viewModel: FavRoutinesViewModel = viewModel(factory = getViewModelFactory())
) {
    val favRoutinesState = viewModel.favRoutinesState

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopOrderAndSearch { order: String, dir: String -> viewModel.orderBy(order, dir) }
            }
            items(favRoutinesState.favRoutines.size) {
                RoutineCard(
                    name = favRoutinesState.favRoutines[it].name,
                    imageUrl = favRoutinesState.favRoutines[it].imageUrl,
                    difficulty = favRoutinesState.favRoutines[it].difficulty,
                    rating = favRoutinesState.favRoutines[it].rating,
                    modifier = Modifier.clickable {
                        onItemClicked(favRoutinesState.favRoutines[it].id)
                    }
                )
            }
        }
    }
}