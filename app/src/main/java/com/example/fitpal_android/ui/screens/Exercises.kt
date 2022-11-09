package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.ui.components.cards.ExerciseCard

@Composable
fun Exercises(
) {


    val exercises = listOf(
        Exercise(
            name = "Bench Press",
            imageUrl = "https://pbs.twimg.com/media/FgHUZjKX0AYlAjw?format=png&name=900x900",
            description = "The bench press is a strength training exercise that consists of pressing a weight upwards from a supine position. The exercise works the pectoralis major, anterior deltoids, and triceps brachii muscles.",
            tags = listOf("Chest", "Barbell"),
        ),
        Exercise(
            name = "Squat",
            imageUrl = "https://pbs.twimg.com/media/FgHUZjKX0AYlAjw?format=png&name=900x900",
            description = "The squat is a compound, full body exercise that trains primarily the muscles of the thighs, hips, buttocks and quads. It is also considered to be an effective cardiovascular exercise.",
            tags = listOf("Legs", "Barbell"),
        ),
        Exercise(
            name = "Skating",
            description = "Cute wooper skating",
            tags = listOf("Chest", "Barbell"),
            imageUrl = "https://pbs.twimg.com/media/FgHUZjKX0AYlAjw?format=png&name=900x900"
        )
    )

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(exercises.size) {
                ExerciseCard(
                    name = exercises[it].name,
                    videoUrl = exercises[it].imageUrl,
                    description = exercises[it].description,
                    tags = exercises[it].tags
                )
            }
        }
    }
}

// TODO: sacar esto cuando tengamos backend
private class Exercise(
    val name: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>
)