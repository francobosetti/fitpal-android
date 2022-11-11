package com.example.fitpal_android.ui.screens

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
import com.example.fitpal_android.data.repository.RoutineRepository
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

    val routine = RoutineRepository().getMyRoutines()[routineId ?: 0]


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