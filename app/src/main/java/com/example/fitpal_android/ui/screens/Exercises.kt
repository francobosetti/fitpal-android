package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitpal_android.data.repository.ExerciseRepository
import com.example.fitpal_android.ui.components.cards.ExerciseCard

@Composable
fun Exercises(
    onItemClicked: (Int) -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val exercises = ExerciseRepository().getExercises()
            items(exercises.size) {
                ExerciseCard(
                    name = exercises[it].name,
                    videoUrl = exercises[it].imageUrl, // TODO: REPLACE IMAGE URL OR VIDEO URL
                    tags = exercises[it].tags,
                    modifier = Modifier.clickable {
                        onItemClicked(it)
                    }
                )
            }
        }
    }
}

