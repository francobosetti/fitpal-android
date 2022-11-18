package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.TopOrderAndSearch
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.ui.screens.authentication.verify.VerifyFormEvent
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ExploreRoutines(
    scaffoldState: ScaffoldState,
    onItemClicked: (Int) -> Unit,
    viewModel: ExploreRoutinesViewModel = viewModel(factory = getViewModelFactory())
) {
    val exploreRoutinesState = viewModel.exploreRoutinesState
    val configuration = LocalConfiguration.current

    // SnackBar used to communicate api Messages
    exploreRoutinesState.apiMsg?.let {
        val message = stringResource(exploreRoutinesState.apiMsg)
        val actionLabel = stringResource(R.string.dismiss)
        LaunchedEffect(scaffoldState.snackbarHostState) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
            when(result) {
                SnackbarResult.Dismissed -> viewModel.dismiss()
                SnackbarResult.ActionPerformed -> viewModel.dismiss()
            }
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        Column {
            Box(modifier = Modifier.padding(8.dp)) {
                TopOrderAndSearch { order: String, dir: String ->
                    viewModel.orderBy(
                        order,
                        dir
                    )
                }
            }
            if (exploreRoutinesState.isFetching) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Orange500,
                        strokeWidth = 4.dp,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            } else {
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        LazyVerticalGrid(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            columns = GridCells.Adaptive(250.dp)
                        ) {
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
                    else -> {
                        LazyColumn(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
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
    }
}
