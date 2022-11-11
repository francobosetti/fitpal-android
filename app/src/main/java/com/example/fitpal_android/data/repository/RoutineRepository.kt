package com.example.fitpal_android.data.repository

class RoutineRepository {

    fun getMyRoutines(): List<MyRoutine> {
        val aux = ExerciseRepository().getExercises()
        return listOf(
            MyRoutine(
                name = "Chest",
                id = 0,
                imageUrl = "https://pbs.twimg.com/media/FfoC1MmWAAkViRz?format=jpg&name=small",
                description = "A routine that will make your chest grow",
                rating = 4.5,
                tags = listOf("Chest", "Arms", "Shoulders"),
                exercises = aux,
                isFavorite = true
            ),
            MyRoutine(
                name = "Legs",
                id = 1,
                imageUrl = "https://pbs.twimg.com/media/Fft9e-FXgAIzIfv?format=jpg&name=small",
                description = "A routine that will make your legs grow",
                rating = 3.5,
                tags = listOf("Legs", "Arms"),
                exercises = aux,
                isFavorite = false
            ),
            MyRoutine(
                name = "Back",
                id = 2,
                imageUrl = "https://pbs.twimg.com/media/Fft_ZgcXwAEuS2-?format=jpg&name=small",
                description = "A routine that will make your back grow",
                rating = 2.5,
                tags = listOf("Back", "Arms"),
                exercises = aux,
                isFavorite = true
            ),
            MyRoutine(
                name = "Arms",
                id = 3,
                imageUrl = "https://pbs.twimg.com/media/Ffzc9nAXEAMdh8T?format=jpg&name=small",
                description = "A routine that will make your arms grow",
                rating = 1.5,
                tags = listOf("Arms", "Shoulders"),
                exercises = aux,
                isFavorite = false
            ),
            MyRoutine(
                name = "Shoulders",
                id = 4,
                imageUrl = "https://pbs.twimg.com/media/Ffi2TERXoAAzFA1?format=jpg&name=4096x4096",
                description = "A routine that will make your shoulders grow",
                rating = 0.5,
                tags = listOf("Shoulders", "Arms"),
                exercises = aux,
                isFavorite = true
            ),
        )
    }
}

// TODO: poner esto en los models?
class MyRoutine(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val tags: List<String>,
    val exercises: List<Exercise>,
    val isFavorite: Boolean,
)
