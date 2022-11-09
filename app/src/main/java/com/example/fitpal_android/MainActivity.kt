package com.example.fitpal_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.navigation.compose.rememberNavController
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import com.example.fitpal_android.ui.screens.Exercises
import com.example.fitpal_android.ui.screens.ExploreRoutines
import com.example.fitpal_android.ui.screens.MyRoutines
import com.example.fitpal_android.ui.screens.Profile
import com.example.fitpal_android.ui.theme.FitpalandroidTheme
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {
                // A surface container using the 'background' color from the theme

                val state = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                // Router current route
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // TODO: HACER ESTO MEJOR
                val title = if (currentRoute == Screens.Profile.route) {
                    Screens.Profile.title
                } else {
                    if (currentRoute == Screens.Exercises.route) {
                        Screens.Exercises.title
                    } else {
                        if (currentRoute == Screens.Routines.route) {
                            Screens.Routines.title
                        } else {
                            if (currentRoute == Screens.ExploreRoutines.route) {
                                Screens.ExploreRoutines.title
                            } else {
                                Screens.Exercises.title
                            }
                        }
                    }
                }


                Scaffold(
                    scaffoldState = state,
                    topBar = {
                        TopBar(
                            title = title,
                            imageUrl = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small",
                            onMenuClick = { scope.launch { state.drawerState.open() } })
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /* TODO */ },
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add Exercise",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    },
                    drawerContent = {
                        NavigationDrawer(
                            navController = navController,
                            onMenuClick = { scope.launch { state.drawerState.close() } }
                        )
                    },
                ) {
                    MyAppNavHost(navController = navController, startDestination = Screens.Exercises.route)
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

@Composable
fun MyAppNavHost(navController: NavHostController = rememberNavController(),
                 startDestination: String = Screens.Exercises.route){

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
    }
}