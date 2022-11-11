package com.example.fitpal_android.data.repository

class RoutineRepository {

    private val defaultId = 1

    private val aux = ExerciseRepository().getExercises()

    private var myRoutines = listOf(
        Routine(
            name = "Chest",
            id = 0,
            imageUrl = "https://pbs.twimg.com/media/FfoC1MmWAAkViRz?format=jpg&name=small",
            description = "A routine that will make your chest grow",
            rating = 4.5,
            tags = listOf("Chest", "Arms", "Shoulders"),
            exercises = aux,
            isFavorite = true
        ),
        Routine(
            name = "Legs",
            id = 1,
            imageUrl = "https://pbs.twimg.com/media/Fft9e-FXgAIzIfv?format=jpg&name=small",
            description = "A routine that will make your legs grow",
            rating = 3.5,
            tags = listOf("Legs", "Arms"),
            exercises = aux,
            isFavorite = false
        ),
        Routine(
            name = "Back",
            id = 2,
            imageUrl = "https://pbs.twimg.com/media/Fft_ZgcXwAEuS2-?format=jpg&name=small",
            description = "A routine that will make your back grow",
            rating = 2.5,
            tags = listOf("Back", "Arms"),
            exercises = aux,
            isFavorite = true
        ),
        Routine(
            name = "Arms",
            id = 3,
            imageUrl = "https://pbs.twimg.com/media/Ffzc9nAXEAMdh8T?format=jpg&name=small",
            description = "A routine that will make your arms grow",
            rating = 1.5,
            tags = listOf("Arms", "Shoulders"),
            exercises = aux,
            isFavorite = false
        ),
        Routine(
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

    private var otherRoutines = listOf(
        Routine(
            name = "Chest",
            id = 0,
            imageUrl = "https://pbs.twimg.com/media/FfoC1MmWAAkViRz?format=jpg&name=small",
            description = "A routine that will make your chest grow",
            rating = 4.5,
            tags = listOf("Chest", "Arms", "Shoulders"),
            exercises = aux,
            isFavorite = true
        ),
        Routine(
            name = "Legs",
            id = 1,
            imageUrl = "https://pbs.twimg.com/media/Fft9e-FXgAIzIfv?format=jpg&name=small",
            description = "A routine that will make your legs grow",
            rating = 3.5,
            tags = listOf("Legs", "Arms"),
            exercises = aux,
            isFavorite = false
        ),
        Routine(
            name = "Back",
            id = 2,
            imageUrl = "https://pbs.twimg.com/media/Fft_ZgcXwAEuS2-?format=jpg&name=small",
            description = "A routine that will make your back grow",
            rating = 2.5,
            tags = listOf("Back", "Arms"),
            exercises = aux,
            isFavorite = true
        ),
        Routine(
            name = "Arms",
            id = 3,
            imageUrl = "https://pbs.twimg.com/media/Ffzc9nAXEAMdh8T?format=jpg&name=small",
            description = "A routine that will make your arms grow",
            rating = 1.5,
            tags = listOf("Arms", "Shoulders"),
            exercises = aux,
            isFavorite = false
        ),
        Routine(
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

    // ------------ GETTERS ------------

    fun getMyRoutines(): List<Routine> {
        return myRoutines
    }

    fun getOtherRoutines(): List<Routine> {
        return otherRoutines
    }

    fun getFavoriteRoutines(): List<Routine> {
        return myRoutines.filter { it.isFavorite }
    }

    fun getRoutineById(id: Int): Routine {
        val routine = myRoutines.find { it.id == id }
        return routine ?: otherRoutines.find { it.id == id } ?: myRoutines[defaultId]
    }

}

// TODO: poner esto en los models?
class Routine(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    var rating: Double,
    val tags: List<String>,
    val exercises: List<Exercise>,
    var isFavorite: Boolean,
)
