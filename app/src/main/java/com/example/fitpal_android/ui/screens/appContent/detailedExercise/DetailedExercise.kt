package com.example.fitpal_android.ui.screens.appContent.detailedExercise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.cards.detailed.DetailedExerciseCard
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun DetailedExercise(
    scaffoldState: ScaffoldState,
    exerciseId: Int?,
    onBackPressed: () -> Unit,
    viewModel: DetailedExerciseViewModel = viewModel(factory = getViewModelFactory(exerciseId))
) {
    val detailedExerciseState = viewModel.detailedExerciseState

    // SnackBar used to communicate api Messages
    detailedExerciseState.apiMsg?.let {
        val message = stringResource(detailedExerciseState.apiMsg)
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

        Column(modifier = Modifier.padding(top = 8.dp)) {


            // Exercise details
            Box(modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)) {
                if (detailedExerciseState.isFetching) {
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
                     if (detailedExerciseState.exercise != null) {
                    DetailedExerciseCard(
                        name = detailedExerciseState.exercise.name,
                        videoUrl = detailedExerciseState.exercise.imageUrl,
                        tags = detailedExerciseState.exercise.tags,
                        description = detailedExerciseState.exercise.description
                    )
                    }
                }
            }
        }

    }
}