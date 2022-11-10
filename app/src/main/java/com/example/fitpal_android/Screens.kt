package com.example.fitpal_android

import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.compose.ui.platform.LocalContext


sealed class Screens(val title: String, val icon: Int, val iconDesc: String,  val route: String) {
    object Exercises: Screens("Exercises", R.drawable.ic_baseline_self_improvement_24 , "exercise", "Exercises")
    object Routines: Screens("My Routines", R.drawable.ic_baseline_sports_gymnastics_24 , "MyRoutine", "Routines")
    object ExploreRoutines: Screens("Explore Routines", R.drawable.ic_baseline_explore_24 , "Routines", "ExploreRoutines")
    object FavoriteRoutine: Screens("Favorite Routines", R.drawable.ic_baseline_star_24, "FavRoutines", "FavRoutines")
    object Profile: Screens("Profile", R.drawable.ic_baseline_person_24 , "Profile", "Profile")
    // TODO : make icons optional
    object DetailedExercise: Screens("Detailed Exercise", R.drawable.ic_baseline_explore_24, "DetailedExercise", "DetailedExercise/{exerciseId}")
    object DetailedRoutine: Screens("Detailed Routine", R.drawable.ic_baseline_explore_24, "DetailedRoutine", "DetailedRoutine/{routineId}")
    object ExecRoutine: Screens("Exec Routine", R.drawable.ic_baseline_explore_24, "ExecRoutine", "ExecRoutine/{routineId}")
    object LogIn: Screens("Log In",R.drawable.ic_baseline_explore_24, "LogIn", "LogIn" )
}