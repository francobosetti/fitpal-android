package com.example.fitpal_android.ui.screens.appContent.favRoutines

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun FavRoutines(
    onItemClicked: (Int) -> Unit,
    viewModel: FavRoutinesViewModel = viewModel(factory = getViewModelFactory())
) {
    val favRoutinesState = viewModel.favRoutinesState
    val configuration = LocalConfiguration.current
    if(favRoutinesState.favRoutines.isEmpty()){
        Text(
            text= stringResource(R.string.no_fav)
        )
    }
    else {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Surface(color = MaterialTheme.colors.background) {
                    LazyVerticalGrid(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        columns = GridCells.Adaptive(250.dp)
                    ) {
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
            else -> {
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
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
        }
    }


}