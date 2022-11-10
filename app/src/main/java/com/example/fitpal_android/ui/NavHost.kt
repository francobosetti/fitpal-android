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
    startDestination: String = Screens.LogIn.route
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Exercises.route) {
            Exercises(onItemClicked = { exerciseId ->
                navController.navigate("DetailedExercise/$exerciseId")
            })
        }
        composable(Screens.Routines.route) {
            ExploreRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }
        composable(Screens.ExploreRoutines.route) {
            MyRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }
        composable(Screens.Profile.route) {
            Profile()
        }
        composable(
            Screens.DetailedExercise.route,
            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType })
        ) {
            DetailedExercise(it.arguments?.getInt("exerciseId"), onBackPressed = {
                navController.popBackStack()
            })
        }
        composable(
            Screens.DetailedRoutine.route,
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) {
            DetailedRoutine(it.arguments?.getInt("routineId"),
                onBackPressed = {
                    navController.popBackStack()
                },
                onStartPressed = { routineId ->
                    navController.navigate("ExecRoutine/$routineId")
                })
        }
        composable(
            Screens.ExecRoutine.route,
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) {
            ExecRoutine(it.arguments?.getInt("routineId"),
                onBackPressed = {
                    navController.popBackStack()
                },)
        }
        composable(Screens.LogIn.route){
            LogIn(onButtonClicked = {navController.navigate(Screens.Exercises.route)})
        }
    }
}