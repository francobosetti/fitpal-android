package com.example.fitpal_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.navigation.AuthNavHost
import com.example.fitpal_android.ui.screens.appContent.detailedExercise.DetailedExerciseViewModel
import com.example.fitpal_android.ui.screens.appContent.detailedRoutine.DetailedRoutineViewModel
import com.example.fitpal_android.ui.screens.appContent.execRoutine.ExecRoutineViewModel
import com.example.fitpal_android.ui.screens.appContent.exercises.ExercisesViewModel
import com.example.fitpal_android.ui.screens.appContent.exploreRoutines.ExploreRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.favRoutines.FavRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.mainScreen.MainScreen
import com.example.fitpal_android.ui.screens.appContent.mainScreen.MainScreenViewModel
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.profile.ProfileViewModel
import com.example.fitpal_android.ui.screens.appContent.settings.SettingsViewModel
import com.example.fitpal_android.ui.theme.FitpalandroidTheme
import com.example.fitpal_android.util.getViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter") // TODO: revisar si esto es relevante
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {
                val scaffoldState: ScaffoldState = rememberScaffoldState()
                val mainActivityViewModel = viewModel<MainActivityViewModel>(factory = getViewModelFactory())

                // View models for main screens
                val mainScreenViewModel = viewModel<MainScreenViewModel>(factory = getViewModelFactory())

                val myRoutinesViewModel = viewModel<MyRoutinesViewModel>(factory = getViewModelFactory())
                val favRoutinesViewModel = viewModel<FavRoutinesViewModel>(factory = getViewModelFactory())
                val exploreRoutinesViewModel =
                    viewModel<ExploreRoutinesViewModel>(factory = getViewModelFactory())
                val exercisesViewModel = viewModel<ExercisesViewModel>(factory = getViewModelFactory())
                val profileViewModel = viewModel<ProfileViewModel>(factory = getViewModelFactory())
                val settingsViewModel = viewModel<SettingsViewModel>(factory = getViewModelFactory())

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {

                    if (mainActivityViewModel.state.isAuthenticated) {
                        MainScreen(
                            scaffoldState = scaffoldState,
                            onLoggedOut = { mainActivityViewModel.loggedOut() },
                            myRoutinesViewModel = myRoutinesViewModel,
                            favRoutinesViewModel = favRoutinesViewModel,
                            exploreRoutinesViewModel = exploreRoutinesViewModel,
                            exercisesViewModel = exercisesViewModel,
                            profileViewModel = profileViewModel,
                            settingsViewModel = settingsViewModel,
                            viewModel = mainScreenViewModel,
                        )
                    } else {
                        AuthNavHost(
                            scaffoldState = scaffoldState,
                            onAuthentication = {
                                // Update view models with new user info
                                myRoutinesViewModel.updateRoutines()
                                favRoutinesViewModel.updateRoutines()
                                exploreRoutinesViewModel.updateRoutines()
                                exercisesViewModel.updateExercises()
                                profileViewModel.updateUser()
                                mainScreenViewModel.updateAvatarUrl()
                                mainActivityViewModel.loggedIn()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitpalandroidTheme {

    }
}

