package com.example.fitpal_android.ui.navigation

import android.content.Intent
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.screens.appContent.execRoutine.ExecRoutine
import com.example.fitpal_android.ui.screens.appContent.exercises.Exercises
import com.example.fitpal_android.ui.screens.appContent.detailedExercise.DetailedExercise
import com.example.fitpal_android.ui.screens.appContent.detailedExercise.DetailedExerciseViewModel
import com.example.fitpal_android.ui.screens.appContent.detailedRoutine.DetailedRoutine
import com.example.fitpal_android.ui.screens.appContent.detailedRoutine.DetailedRoutineViewModel
import com.example.fitpal_android.ui.screens.appContent.execRoutine.ExecRoutineViewModel
import com.example.fitpal_android.ui.screens.appContent.exercises.ExercisesViewModel
import com.example.fitpal_android.ui.screens.appContent.exploreRoutines.ExploreRoutines
import com.example.fitpal_android.ui.screens.appContent.exploreRoutines.ExploreRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.favRoutines.FavRoutines
import com.example.fitpal_android.ui.screens.appContent.favRoutines.FavRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutines
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.profile.Profile
import com.example.fitpal_android.ui.screens.appContent.profile.ProfileViewModel
import com.example.fitpal_android.ui.screens.appContent.settings.Settings
import com.example.fitpal_android.ui.screens.appContent.settings.SettingsViewModel
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun AppContentNavHost(
    scaffoldState: ScaffoldState,
    navController: NavHostController = rememberNavController(),
    onProfileUpdate: () -> Unit,
    myRoutinesViewModel: MyRoutinesViewModel,
    favRoutinesViewModel: FavRoutinesViewModel,
    exploreRoutinesViewModel: ExploreRoutinesViewModel,
    exercisesViewModel: ExercisesViewModel,
    profileViewModel: ProfileViewModel,
    settingsViewModel: SettingsViewModel,
) {
    val currentContext = LocalContext.current

    NavHost(navController = navController, startDestination = Screens.Exercises.route) {

        // ------------ Main screens -------------

        // Exercises
        composable(Screens.Exercises.route) {
            Exercises(
                onItemClicked = { exerciseId ->
                    navController.navigate("DetailedExercise/$exerciseId")
                },
                viewModel = exercisesViewModel
            )
        }

        // Explore Routines
        composable(Screens.ExploreRoutines.route) {
            ExploreRoutines(
                scaffoldState = scaffoldState,
                onItemClicked = { routineId ->
                    navController.navigate("DetailedRoutine/$routineId")
                },
                viewModel = exploreRoutinesViewModel
            )
        }

        // Favorite Routines
        composable(Screens.FavoriteRoutine.route) {
            FavRoutines(
                scaffoldState = scaffoldState,
                onItemClicked = { routineId ->
                    navController.navigate("DetailedRoutine/$routineId")
                },
                viewModel = favRoutinesViewModel
            )
        }

        // My Routines
        composable(Screens.Routines.route) {
            MyRoutines(
                scaffoldState = scaffoldState,
                onItemClicked = { routineId ->
                    navController.navigate("DetailedRoutine/$routineId")
                },
                viewModel = myRoutinesViewModel
            )
        }

        // Profile
        composable(Screens.Profile.route) {
            Profile(
                scaffoldState = scaffoldState,
                onProfileUpdate = onProfileUpdate,
                viewModel = profileViewModel
            )
        }

        // Settings
        composable(Screens.Settings.route) {
            Settings(viewModel = settingsViewModel)
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
            DetailedExercise(
                scaffoldState = scaffoldState,
                exerciseId = it.arguments?.getInt("exerciseId"),
                onBackPressed = { navController.popBackStack() },
            )
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
            DetailedRoutine(
                routineId = it.arguments?.getInt("routineId"),
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
                    myRoutinesViewModel.updateRoutines()
                    exploreRoutinesViewModel.updateRoutines()
                    favRoutinesViewModel.updateRoutines()
                },
                onRatingSubmit = {
                    myRoutinesViewModel.updateRoutines()
                    exploreRoutinesViewModel.updateRoutines()
                    favRoutinesViewModel.updateRoutines()
                },
            )
        }

        // Exec Routine
        composable(
            Screens.ExecRoutine.route,
            arguments = listOf(navArgument("routineId") { type = NavType.IntType })
        ) {
            ExecRoutine(
                routineId = it.arguments?.getInt("routineId"),
                onBackPressed = {
                    navController.popBackStack()
                },
            )
        }
    }
}