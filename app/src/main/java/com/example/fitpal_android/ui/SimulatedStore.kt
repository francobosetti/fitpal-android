package com.example.fitpal_android.ui

class SimulatedStore {

    companion object {
        fun myRoutines(): List<MyRoutine> {
            return listOf(
                MyRoutine(
                    name = "Chest",
                    imageUrl = "https://pbs.twimg.com/media/FfoC1MmWAAkViRz?format=jpg&name=small",
                    description = "A routine that will make your chest grow",
                    rating = 4.5,
                ),
                MyRoutine(
                    name = "Legs",
                    imageUrl = "https://pbs.twimg.com/media/Fft9e-FXgAIzIfv?format=jpg&name=small",
                    description = "A routine that will make your legs grow",
                    rating = 3.5,
                ),
                MyRoutine(
                    name = "Back",
                    imageUrl = "https://pbs.twimg.com/media/Fft_ZgcXwAEuS2-?format=jpg&name=small",
                    description = "A routine that will make your back grow",
                    rating = 2.5,
                ),
                MyRoutine(
                    name = "Arms",
                    imageUrl = "https://pbs.twimg.com/media/Ffzc9nAXEAMdh8T?format=jpg&name=small",
                    description = "A routine that will make your arms grow",
                    rating = 1.5,
                ),
                MyRoutine(
                    name = "Shoulders",
                    imageUrl = "https://pbs.twimg.com/media/Ffi2TERXoAAzFA1?format=jpg&name=4096x4096",
                    description = "A routine that will make your shoulders grow",
                    rating = 0.5,
                ),
            )
        }

        fun myExercises(): List<Exercise> {
            return listOf(
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
        }

    }
}

// TODO: sacar esto cuando tengamos backend
class MyRoutine(
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double
)

// TODO: sacar esto cuando tengamos backend
class Exercise(
    val name: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>
)
