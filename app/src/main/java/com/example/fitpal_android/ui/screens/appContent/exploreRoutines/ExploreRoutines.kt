package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.ui.components.cards.RoutineCard

@Composable
fun ExploreRoutines(
    onItemClicked: (Int) -> Unit
) {
    val viewModel = ExploreRoutinesViewModel()
    val otherRoutines = viewModel.getOtherRoutines()

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(otherRoutines.size) {
                RoutineCard(
                    name = otherRoutines[it].name,
                    imageUrl = otherRoutines[it].imageUrl,
                    tags = otherRoutines[it].tags,
                    rating = otherRoutines[it].rating,
                    modifier = Modifier.clickable {
                        onItemClicked(otherRoutines[it].id)
                    }
                )
            }
        }
    }
}
