package com.example.fitpal_android.ui

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.screens.*

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.LogIn.route
) {

    val currentContext = LocalContext.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Exercises.route) {
            Exercises(onItemClicked = { exerciseId ->
                navController.navigate("DetailedExercise/$exerciseId")
            })
        }
        composable(Screens.ExploreRoutines.route) {
            ExploreRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }
        composable(Screens.FavoriteRoutine.route) {
            FavRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }
        composable(Screens.Routines.route) {
            MyRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }
        composable(Screens.Profile.route) {
            Profile()
        }
        composable(
            Screens.DetailedExercise.route,
            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "fitpal://exercise/{exerciseId}"
                action = Intent.ACTION_VIEW
            })
        ) {
            // TODO: hacer que si estas en deep link, si vas para atras te lleve a la pantalla de ejercicios
            DetailedExercise(it.arguments?.getInt("exerciseId"), onBackPressed = {
                navController.popBackStack()
            })
        }
        composable(
            Screens.DetailedRoutine.route,
            arguments = listOf(navArgument("routineId") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "fitpal://routines/{routineId}" // TODO: Revisar link
                action = Intent.ACTION_VIEW


            })
        ) {
            // TODO: hacer que si estas en deep link, si vas para atras te lleve a la pantalla de rutinas

            DetailedRoutine(it.arguments?.getInt("routineId"),
                onBackPressed = {
                    navController.popBackStack()
                },
                onStartPressed = { routineId ->
                    navController.navigate("ExecRoutine/$routineId")
                },
                onSharePressed = { routineId ->
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "fitpal://routines/$routineId")
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TITLE, "FitPal Routine")
                    }
                    val shareIntent = Intent.createChooser(intent, null)
                    currentContext.startActivity(shareIntent)
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
        composable(Screens.LogIn.route) {
            LogIn(onButtonClicked = { navController.navigate(Screens.Exercises.route) })
        }
    }
}