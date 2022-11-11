package com.example.fitpal_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitpal_android.ui.MyAppNavHost
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import com.example.fitpal_android.ui.theme.FitpalandroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    // TODO: Ver Que onda con este warning
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {
                // A surface container using the 'background' color from the theme

                val state = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val topBarState= rememberSaveable{(mutableStateOf(true))}
                val navBarState= rememberSaveable{(mutableStateOf(true))}
                // Router current route
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                when(navBackStackEntry?.destination?.route){
                    Screens.LogIn.route -> {
                        topBarState.value=false
                        navBarState.value=false
                    }
                    else -> {
                        topBarState.value =true
                        navBarState.value= true
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
                                onMenuClick = { scope.launch { state.drawerState.close() } }
                            )
                        }
                    },
                ) {
                    MyAppNavHost(
                        navController = navController,
                        startDestination = Screens.LogIn.route
                    )
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

