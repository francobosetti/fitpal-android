package com.example.fitpal_android.ui.screens.appContent.detailedExercise

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
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun DetailedExercise(
    exerciseId: Int?,
    onBackPressed: () -> Unit,
    viewModel: DetailedExerciseViewModel
) {
    val detailedExerciseState = viewModel.detailedExerciseState

    Surface(color = MaterialTheme.colors.background) {

        Column(modifier = Modifier.padding(top = 8.dp)) {


            // Exercise details
            Box(modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)) {

                if (detailedExerciseState.exercise != null) {
                    DetailedExerciseCard(
                        name = detailedExerciseState.exercise.name,
                        videoUrl = detailedExerciseState.exercise.videoUrl,
                        tags = detailedExerciseState.exercise.tags,
                        description = detailedExerciseState.exercise.description
                    )
                }
            }
        }

    }
}