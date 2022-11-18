package com.example.fitpal_android.ui.screens.appContent.myRoutines

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.TopOrderAndSearch
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun MyRoutines(
    onItemClicked: (Int) -> Unit,
    viewModel: MyRoutinesViewModel = viewModel(factory = getViewModelFactory())
) {
    val myRoutinesState = viewModel.myRoutinesState

    val configuration = LocalConfiguration.current
    when(configuration.orientation){
        Configuration.ORIENTATION_LANDSCAPE ->{
            Surface(color = MaterialTheme.colors.background) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    columns = GridCells.Adaptive(250.dp)
                ) {
                    item {
                        TopOrderAndSearch { order: String, dir: String -> viewModel.orderBy(order, dir) }
                    }
                    items(myRoutinesState.myRoutines.size) {
                        RoutineCard(
                            name = myRoutinesState.myRoutines[it].name,
                            imageUrl = myRoutinesState.myRoutines[it].imageUrl,
                            difficulty = myRoutinesState.myRoutines[it].difficulty,
                            rating = myRoutinesState.myRoutines[it].rating,
                            modifier = Modifier.clickable {
                                onItemClicked(myRoutinesState.myRoutines[it].id)
                            }
                        )
                    }
                }
            }
        }
        else ->{
            Surface(color = MaterialTheme.colors.background) {
                LazyColumn(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        TopOrderAndSearch { order: String, dir: String -> viewModel.orderBy(order, dir) }
                    }
                    items(myRoutinesState.myRoutines.size) {
                        RoutineCard(
                            name = myRoutinesState.myRoutines[it].name,
                            imageUrl = myRoutinesState.myRoutines[it].imageUrl,
                            difficulty = myRoutinesState.myRoutines[it].difficulty,
                            rating = myRoutinesState.myRoutines[it].rating,
                            modifier = Modifier.clickable {
                                onItemClicked(myRoutinesState.myRoutines[it].id)
                            }
                        )
                    }
                }
            }
        }
    }
}