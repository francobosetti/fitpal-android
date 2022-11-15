package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.TopOrderAndSearch
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun ExploreRoutines(
    onItemClicked: (Int) -> Unit
) {
    val viewModel = viewModel<ExploreRoutinesViewModel>(factory = getViewModelFactory() )
    val exploreRoutinesState = viewModel.exploreRoutinesState

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopOrderAndSearch()
            }
            items(exploreRoutinesState.otherRoutines.size) {
                RoutineCard(
                    name = exploreRoutinesState.otherRoutines[it].name,
                    imageUrl = exploreRoutinesState.otherRoutines[it].imageUrl,
                    tags = exploreRoutinesState.otherRoutines[it].tags,
                    rating = exploreRoutinesState.otherRoutines[it].rating,
                    modifier = Modifier.clickable {
                        onItemClicked(exploreRoutinesState.otherRoutines[it].id)
                    }
                )
            }
        }
    }
}
