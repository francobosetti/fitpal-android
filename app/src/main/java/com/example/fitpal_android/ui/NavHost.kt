package com.example.fitpal_android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.screens.*

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Exercises.route
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Exercises.route) {
            Exercises()
        }
        composable(Screens.Routines.route) {
            ExploreRoutines()
        }
        composable(Screens.ExploreRoutines.route) {
            MyRoutines()
        }
        composable(Screens.Profile.route) {
            Profile()
        }
        composable(
            Screens.DetailedExercise.route,
            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType })
        ) {
            DetailedExercise(it.arguments?.getInt("exerciseId"))
        }
        composable(
            Screens.DetailedRoutine.route,
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) {
            DetailedRoutine(it.arguments?.getInt("routineId"))
        }
    }
}