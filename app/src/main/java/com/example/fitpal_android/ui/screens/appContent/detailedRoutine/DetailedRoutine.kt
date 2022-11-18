package com.example.fitpal_android.ui.screens.appContent.detailedRoutine

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.cards.detailed.DetailedRoutineCard
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun DetailedRoutine(
    routineId: Int?,
    onStartPressed: (Int?) -> Unit,
    onSharePressed: (Int?) -> Unit,
    onFavoritePressed: () -> Unit,
    onRatingSubmit: () -> Unit,
    viewModel: DetailedRoutineViewModel = viewModel(factory = getViewModelFactory(routineId))
) {

    val detailedRoutineState = viewModel.detailedRoutineState
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->


            when (event) {
                is DetailedRoutineEvent.RoutineRated -> {
                    Toast.makeText(
                        context,
                        "Routine rated successfully",
                        Toast.LENGTH_LONG
                    ).show()

                    onRatingSubmit()

                }
                is DetailedRoutineEvent.FavoriteToggled -> {

                    Toast.makeText(
                        context,
                        "Routine favourite toggled successfully",
                        Toast.LENGTH_LONG
                    ).show()

                    onFavoritePressed()
                }
            }
        }
    }


    Surface(color = MaterialTheme.colors.background) {

        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {

            if (routineId != null && detailedRoutineState.routine != null) {
                detailedRoutineState.routine

                // Routine details
                DetailedRoutineCard(
                    name = detailedRoutineState.routine.name,
                    description = detailedRoutineState.routine.description,
                    difficulty = detailedRoutineState.routine.difficulty,
                    imageUrl = detailedRoutineState.routine.imageUrl,
                    modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                    rating = detailedRoutineState.routine.rating,
                    selectedRating = detailedRoutineState.userRating,
                    isFavorite = detailedRoutineState.routine.isFavorite,
                    onStartPressedCallback = { onStartPressed(routineId) },
                    onSharePressedCallback = { onSharePressed(routineId) },
                    onFavoritePressedCallback = { viewModel.toggleFav() },
                    onRatingSubmitCallback = { rating -> viewModel.rateRoutine(rating) },
                    showPopup = detailedRoutineState.showPopup,
                    onDismissPopupCallback = { viewModel.dismissPopup() },
                    onShowPopupCallback = { viewModel.showPopup() },
                    onUpdateSelectedRatingCallback = {rating -> viewModel.updateUserRating(rating) },
                )
            }

        }

    }
}