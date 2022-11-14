package com.example.fitpal_android.ui.screens.appContent.favRoutines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.ui.components.TopOrderAndSearch
import com.example.fitpal_android.ui.components.cards.RoutineCard

@Composable
fun FavRoutines(
    onItemClicked: (Int) -> Unit
) {
    val viewModel = FavRoutinesViewModel()
    val favRoutines = viewModel.getFavRoutines()

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopOrderAndSearch()
            }
            items(favRoutines.size) {
                RoutineCard(
                    name = favRoutines[it].name,
                    imageUrl = favRoutines[it].imageUrl,
                    tags = favRoutines[it].tags,
                    rating = favRoutines[it].rating,
                    modifier = Modifier.clickable {
                        onItemClicked(favRoutines[it].id)
                    }
                )
            }
        }
    }
}