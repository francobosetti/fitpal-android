package com.example.fitpal_android.ui.screens.detailedExercise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.components.cards.detailed.DetailedExerciseCard

@Composable
fun DetailedExercise(
    exerciseId: Int?,
    onBackPressed: () -> Unit
) {
    val viewModel = viewModel<DetailedExerciseViewModel>()
    viewModel.initialize(exerciseId)
    val exercise = viewModel.getExercise()

    Surface(color = MaterialTheme.colors.background) {

        Column {
            // Back button
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }

            // Exercise details
            Box(modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)) {
                DetailedExerciseCard(
                    name = exercise.name,
                    description = exercise.description,
                    tags = exercise.tags,
                    videoUrl = exercise.imageUrl
                )
            }
        }

    }
}