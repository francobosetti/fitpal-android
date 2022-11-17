package com.example.fitpal_android.ui.screens.appContent.mainScreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitpal_android.FitpalApplication
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.navigation.AppContentNavHost
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import com.example.fitpal_android.ui.screens.appContent.mainScreen.MainScreenViewModel
import com.example.fitpal_android.util.getViewModelFactory
import kotlinx.coroutines.launch

// TODO: Ver Que onda con este warning
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    scaffoldState: ScaffoldState,
    onLoggedOut: () -> Unit
) {
    // A surface container using the 'background' color from the theme
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBarState = rememberSaveable { (mutableStateOf(true)) }

    // Router current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        Screens.LogIn.route -> {
            topBarState.value = false
            navBarState.value = false
        }
        else -> {
            topBarState.value = true
            navBarState.value = true
        }
    }

    val viewModel = viewModel<MainScreenViewModel>(
        factory = getViewModelFactory()
    )
    val mainScreenState = viewModel.mainScreenState

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AnimatedVisibility(visible = topBarState.value) {
                TopBar(
                    title = when (navBackStackEntry?.destination?.route) {
                        Screens.Profile.route -> stringResource(Screens.Profile.title)
                        Screens.Exercises.route -> stringResource(Screens.Exercises.title)
                        Screens.Routines.route -> stringResource(Screens.Routines.title)
                        Screens.ExploreRoutines.route -> stringResource(Screens.ExploreRoutines.title)
                        Screens.DetailedExercise.route -> stringResource(Screens.DetailedExercise.title)
                        Screens.DetailedRoutine.route -> stringResource(Screens.DetailedRoutine.title)
                        Screens.ExecRoutine.route -> stringResource(Screens.ExecRoutine.title)
                        Screens.FavoriteRoutine.route -> stringResource(Screens.FavoriteRoutine.title)
                        Screens.Settings.route -> stringResource(Screens.Settings.title)
                        else -> "FitPal"
                    },
                    imageUrl = mainScreenState.avatarUrl,
                    onMenuClick = { scope.launch { scaffoldState.drawerState.open() } },
                    navController = navController
                )
            }

        },
        drawerContent = {
            AnimatedVisibility(visible = navBarState.value) {
                NavigationDrawer(
                    navController = navController,
                    onMenuClick = { scope.launch { scaffoldState.drawerState.close() } },
                    onLogOutClick = { scope.launch { viewModel.logout(); onLoggedOut() } }
                )
            }
        },
    ) {
        AppContentNavHost(
            navController = navController,
            onProfileUpdate = { scope.launch { viewModel.updateAvatarUrl() } },
        )
    }
}