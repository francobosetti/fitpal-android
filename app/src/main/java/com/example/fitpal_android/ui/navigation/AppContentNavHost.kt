package com.example.fitpal_android.ui.navigation

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
import com.example.fitpal_android.ui.screens.appContent.ExecRoutine
import com.example.fitpal_android.ui.screens.appContent.exercises.Exercises
import com.example.fitpal_android.ui.screens.appContent.detailedExercise.DetailedExercise
import com.example.fitpal_android.ui.screens.appContent.detailedRoutine.DetailedRoutine
import com.example.fitpal_android.ui.screens.appContent.exploreRoutines.ExploreRoutines
import com.example.fitpal_android.ui.screens.appContent.favRoutines.FavRoutines
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutines
import com.example.fitpal_android.ui.screens.appContent.profile.Profile

@Composable
fun AppContentNavHost(
    navController: NavHostController = rememberNavController(),
) {

    val currentContext = LocalContext.current

    NavHost(navController = navController, startDestination = Screens.Exercises.route) {

        // ------------ Main screens -------------

        // Exercises
        composable(Screens.Exercises.route) {
            Exercises(onItemClicked = { exerciseId ->
                navController.navigate("DetailedExercise/$exerciseId")
            })
        }

        // Explore Routines
        composable(Screens.ExploreRoutines.route) {
            ExploreRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }

        // Favorite Routines
        composable(Screens.FavoriteRoutine.route) {
            FavRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }

        // My Routines
        composable(Screens.Routines.route) {
            MyRoutines(onItemClicked = { routineId ->
                navController.navigate("DetailedRoutine/$routineId")
            })
        }

        // Profile
        composable(Screens.Profile.route) {
            Profile()
        }

        // ------------ Secondary screens -------------
        // Detailed Exercise
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

        // Detailed Routine
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
                },
                onFavoritePressed = {
                    // TODO: implementar favoritos
                },
                onRatingSubmit = { routineId, rating ->
                    // TODO: impementar submit ratings
                }
            )
        }

        // Exec Routine
        composable(
            Screens.ExecRoutine.route,
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) {
            ExecRoutine(
                it.arguments?.getInt("routineId"),
                onBackPressed = {
                    navController.popBackStack()
                },
            )
        }
    }
}