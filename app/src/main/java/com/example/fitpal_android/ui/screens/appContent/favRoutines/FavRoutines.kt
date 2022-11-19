package com.example.fitpal_android.ui.screens.appContent.favRoutines

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun FavRoutines(
    scaffoldState: ScaffoldState,
    onItemClicked: (Int) -> Unit,
    viewModel: FavRoutinesViewModel
) {
    val favRoutinesState = viewModel.favRoutinesState
    val configuration = LocalConfiguration.current

    // SnackBar used to communicate api Messages
    favRoutinesState.apiMsg?.let {
        val message = stringResource(favRoutinesState.apiMsg)
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
    if (favRoutinesState.isFetching) {
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
        if (favRoutinesState.favRoutines.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_fav)
                )
            }
        } else {
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

}