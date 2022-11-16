package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.TopOrderAndSearch
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ExploreRoutines(
    onItemClicked: (Int) -> Unit,
    viewModel: ExploreRoutinesViewModel = viewModel(factory = getViewModelFactory())
) {
    val exploreRoutinesState = viewModel.exploreRoutinesState
    val configuration = LocalConfiguration.current
    when(configuration.orientation){
        Configuration.ORIENTATION_LANDSCAPE ->{
            Surface(color = MaterialTheme.colors.background) {
                LazyRow(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        TopOrderAndSearch { order, dir -> viewModel.orderBy(order, dir)}
                    }
                    items(exploreRoutinesState.otherRoutines.size) {
                        RoutineCard(
                            name = exploreRoutinesState.otherRoutines[it].name,
                            imageUrl = exploreRoutinesState.otherRoutines[it].imageUrl,
                            rating = exploreRoutinesState.otherRoutines[it].rating,
                            difficulty = exploreRoutinesState.otherRoutines[it].difficulty,
                            modifier = Modifier.clickable {
                                onItemClicked(exploreRoutinesState.otherRoutines[it].id)
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
                        TopOrderAndSearch { order, dir -> viewModel.orderBy(order, dir)}
                    }
                    items(exploreRoutinesState.otherRoutines.size) {
                        RoutineCard(
                            name = exploreRoutinesState.otherRoutines[it].name,
                            imageUrl = exploreRoutinesState.otherRoutines[it].imageUrl,
                            rating = exploreRoutinesState.otherRoutines[it].rating,
                            difficulty = exploreRoutinesState.otherRoutines[it].difficulty,
                            modifier = Modifier.clickable {
                                onItemClicked(exploreRoutinesState.otherRoutines[it].id)
                            }
                        )
                    }
                }
            }
        }
    }


}
