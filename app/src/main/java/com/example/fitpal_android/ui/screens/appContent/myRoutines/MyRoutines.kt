package com.example.fitpal_android.ui.screens.appContent.myRoutines

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.OrderByDropdown
import com.example.fitpal_android.ui.components.cards.RoutineCard
import com.example.fitpal_android.ui.theme.Orange500

@Composable
fun MyRoutines(
    scaffoldState: ScaffoldState,
    onItemClicked: (Int) -> Unit,
    viewModel: MyRoutinesViewModel
) {
    val myRoutinesState = viewModel.myRoutinesState
    val configuration = LocalConfiguration.current

    // SnackBar used to communicate api Messages
    myRoutinesState.apiMsg?.let {
        val message = stringResource(myRoutinesState.apiMsg)
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
        Column{
            Box(modifier = Modifier.padding(20.dp)) {
                OrderByDropdown{ order: String, dir: String ->
                    viewModel.orderBy(
                        order,
                        dir
                    )
                }
            }
            if(myRoutinesState.isFetching) {
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
                if (myRoutinesState.myRoutines.isEmpty()) {
                    Text( text= stringResource(R.string.no_routines))
                } else {
                    when (configuration.orientation) {
                        Configuration.ORIENTATION_LANDSCAPE -> {
                            LazyVerticalGrid(
                                modifier = Modifier.padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                columns = GridCells.Adaptive(250.dp)
                            ) {
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

                        else -> {
                            LazyColumn(
                                modifier = Modifier.padding(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
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
        }

    }
}