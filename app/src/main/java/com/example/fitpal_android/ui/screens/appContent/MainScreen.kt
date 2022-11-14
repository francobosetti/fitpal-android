package com.example.fitpal_android.ui.screens.appContent

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitpal_android.FitpalApplication
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.navigation.AppContentNavHost
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import kotlinx.coroutines.launch

// TODO: Ver Que onda con este warning
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(onLoggedOut: () -> Unit) {
    // A surface container using the 'background' color from the theme
    // TODO: Viewmodel de esto
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBarState = rememberSaveable { (mutableStateOf(true)) }
    val application = (LocalContext.current.applicationContext as FitpalApplication)

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

    Scaffold(
        scaffoldState = state,
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
                        else -> "FitPal"
                    },
                    imageUrl = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small",
                    onMenuClick = { scope.launch { state.drawerState.open() } },
                    navController = navController
                )
            }

        },
        drawerContent = {
            AnimatedVisibility(visible = navBarState.value) {
                NavigationDrawer(
                    navController = navController,
                    onMenuClick = { scope.launch { state.drawerState.close() } },
                    // TODO: creo que esto esta mal
                    onLogOutClick = { scope.launch { application.userRepository.logout(); onLoggedOut() } }
                )
            }
        },
    ) {
        AppContentNavHost(
            navController = navController,
        )
    }
}