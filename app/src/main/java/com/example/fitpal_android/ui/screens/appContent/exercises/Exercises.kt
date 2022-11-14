package com.example.fitpal_android.ui.screens.appContent.exercises

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.ui.components.cards.ExerciseCard
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun Exercises(
    onItemClicked: (Int) -> Unit
) {
    val viewModel = viewModel<ExercisesViewModel>(factory = getViewModelFactory())
    val exercisesState = viewModel.exercisesState


    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(exercisesState.exercises.size) {
                ExerciseCard(
                    name = exercisesState.exercises[it].name,
                    videoUrl = exercisesState.exercises[it].videoUrl, // TODO: REPLACE IMAGE URL OR VIDEO URL
                    tags = exercisesState.exercises[it].tags,
                    modifier = Modifier.clickable {
                        onItemClicked(exercisesState.exercises[it].id)
                    }
                )
            }
        }
    }
}

