package com.example.fitpal_android.ui.screens.appContent.detailedRoutine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.cards.detailed.DetailedRoutineCard

@Composable
fun DetailedRoutine(
    routineId: Int?,
    onBackPressed: () -> Unit,
    onStartPressed: (Int?) -> Unit,
    onSharePressed: (Int?) -> Unit,
    onFavoritePressed: (Int?) -> Unit,
    onRatingSubmit: (Int?, Double) -> Unit
) {

    val viewModel = viewModel<DetailedRoutineViewModel>()
    viewModel.initialize(routineId)
    val routine = viewModel.getRoutine()


    Surface(color = MaterialTheme.colors.background) {

        Column {
            // Back button
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }

            // Routine details
            DetailedRoutineCard(
                name = routine.name,
                description = routine.description,
                tags = routine.tags,
                videoUrl = routine.imageUrl,
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                rating = routine.rating,
                isFavorite = routine.isFavorite,
                onStartPressedCallback = { onStartPressed(routineId) },
                onSharePressedCallback = { onSharePressed(routineId) },
                onFavoritePressedCallback = { onFavoritePressed(routineId) },
                onRatingSubmitCallback = { rating -> onRatingSubmit(routineId, rating) }
            )
        }

    }
}