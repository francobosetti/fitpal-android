package com.example.fitpal_android.ui

class SimulatedStore {

    companion object {
        fun myRoutines(): List<MyRoutine> {
            return listOf(
                MyRoutine(
                    name = "Chest",
                    id = 0,
                    imageUrl = "https://pbs.twimg.com/media/FfoC1MmWAAkViRz?format=jpg&name=small",
                    description = "A routine that will make your chest grow",
                    rating = 4.5,
                    tags = listOf("Chest", "Arms", "Shoulders"),
                    exercises = listOf(myExercises()[0], myExercises()[1], myExercises()[2])
                ),
                MyRoutine(
                    name = "Legs",
                    id = 1,
                    imageUrl = "https://pbs.twimg.com/media/Fft9e-FXgAIzIfv?format=jpg&name=small",
                    description = "A routine that will make your legs grow",
                    rating = 3.5,
                    tags = listOf("Legs", "Arms"),
                    exercises = listOf(myExercises()[0], myExercises()[1], myExercises()[2])
                ),
                MyRoutine(
                    name = "Back",
                    id = 2,
                    imageUrl = "https://pbs.twimg.com/media/Fft_ZgcXwAEuS2-?format=jpg&name=small",
                    description = "A routine that will make your back grow",
                    rating = 2.5,
                    tags = listOf("Back", "Arms"),
                    exercises = listOf(myExercises()[0], myExercises()[1], myExercises()[2])
                ),
                MyRoutine(
                    name = "Arms",
                    id = 3,
                    imageUrl = "https://pbs.twimg.com/media/Ffzc9nAXEAMdh8T?format=jpg&name=small",
                    description = "A routine that will make your arms grow",
                    rating = 1.5,
                    tags = listOf("Arms", "Shoulders"),
                    exercises = listOf(myExercises()[0], myExercises()[1], myExercises()[2])
                ),
                MyRoutine(
                    name = "Shoulders",
                    id = 4,
                    imageUrl = "https://pbs.twimg.com/media/Ffi2TERXoAAzFA1?format=jpg&name=4096x4096",
                    description = "A routine that will make your shoulders grow",
                    rating = 0.5,
                    tags = listOf("Shoulders", "Arms"),
                    exercises = listOf(myExercises()[0], myExercises()[1], myExercises()[2])
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
}

// TODO: sacar esto cuando tengamos backend
class MyRoutine(
    val name: String,
    val id : Int,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val tags: List<String>,
    val exercises : List<Exercise>
)

// TODO: sacar esto cuando tengamos backend
class Exercise(
    val name: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val seconds : Int
)
