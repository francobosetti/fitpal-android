package com.example.fitpal_android

sealed class Screens(val title: String, val icon: Int, val iconDesc: String,  val route: String) {
    object Exercises: Screens("Exercises", R.drawable.ic_baseline_self_improvement_24 , "exercise", "Exercises")
    object Routines: Screens("My Routines", R.drawable.ic_baseline_sports_gymnastics_24 , "MyRoutine", "Routines")
    object ExploreRoutines: Screens("Explore Routines", R.drawable.ic_baseline_explore_24 , "Routines", "ExploreRoutines")
    object Profile: Screens("Profile", R.drawable.ic_baseline_person_24 , "Profile", "Profile")
    // TODO : make icons optional
    object DetailedExercise: Screens("Detailed Exercise", R.drawable.ic_baseline_explore_24, "DetailedExercise", "DetailedExercise/{exerciseId}")
    object DetailedRoutine: Screens("Detailed Routine", R.drawable.ic_baseline_explore_24, "DetailedRoutine", "DetailedRoutine/{routineId}")
    object ExecRoutine: Screens("Exec Routine", R.drawable.ic_baseline_explore_24, "ExecRoutine", "ExecRoutine/{routineId}")
}