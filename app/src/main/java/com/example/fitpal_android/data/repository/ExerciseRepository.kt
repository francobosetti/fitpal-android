package com.example.fitpal_android.data.repository

class ExerciseRepository {
    fun getExercises() : List<Exercise> {
        return listOf(
            Exercise(
                name = "Bench Press",
                imageUrl = "https://pbs.twimg.com/media/FgHUZjKX0AYlAjw?format=png&name=900x900",
                description = "The bench press is a strength training exercise that consists of pressing a weight upwards from a supine position. The exercise works the pectoralis major, anterior deltoids, and triceps brachii muscles.",
                tags = listOf("Chest", "Barbell"),
                seconds = 5
            ),
            Exercise(
                name = "Squat",
                imageUrl = "https://pbs.twimg.com/media/FgHUZjKX0AYlAjw?format=png&name=900x900",
                description = "The squat is a compound, full body exercise that trains primarily the muscles of the thighs, hips, buttocks and quads. It is also considered to be an effective cardiovascular exercise.",
                tags = listOf("Legs", "Barbell"),
                seconds = 6
            ),
            Exercise(
                name = "Skating",
                description = "Cute wooper skating",
                tags = listOf("Chest", "Barbell"),
                imageUrl = "https://pbs.twimg.com/media/FgHUZjKX0AYlAjw?format=png&name=900x900",
                seconds = 4
            )
        )
    }
}


// TODO: poner esto en los models?
class Exercise(
    val name: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val seconds : Int
)
