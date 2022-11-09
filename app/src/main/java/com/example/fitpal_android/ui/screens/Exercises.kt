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
import com.example.fitpal_android.ui.SimulatedStore
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
            items(SimulatedStore.myExercises().size) {
                ExerciseCard(
                    name = SimulatedStore.myExercises()[it].name,
                    videoUrl = SimulatedStore.myExercises()[it].imageUrl,
                    tags = SimulatedStore.myExercises()[it].tags,
                    modifier = Modifier.clickable {
                        onItemClicked(it)
                    }
                )
            }
        }
    }
}

