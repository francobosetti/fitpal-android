package com.example.fitpal_android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val title: String, val icon: Int, val iconDesc: String,  val route: String) {
    object Exercises: Screens("Exercises", R.drawable.ic_baseline_self_improvement_24 , "exercise", "Exercises")
    object Routines: Screens("My Routines", R.drawable.ic_baseline_sports_gymnastics_24 , "MyRoutine", "Routines")
    object ExploreRoutines: Screens("Explore Routines", R.drawable.ic_baseline_explore_24 , "Routines", "ExploreRoutines")
    object Profile: Screens("Profile", R.drawable.ic_baseline_person_24 , "Profile", "Profile")
}