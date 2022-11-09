package com.example.fitpal_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitpal_android.ui.MyAppNavHost
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import com.example.fitpal_android.ui.theme.FitpalandroidTheme
import kotlinx.coroutines.launch

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


                val title = when (navBackStackEntry?.destination?.route) {
                    Screens.Profile.route -> Screens.Profile.title
                    Screens.Exercises.route -> Screens.Exercises.title
                    Screens.Routines.route -> Screens.Routines.title
                    Screens.ExploreRoutines.route -> Screens.ExploreRoutines.title
                    else -> Screens.Exercises.title
                }

                Scaffold(
                    scaffoldState = state,
                    topBar = {
                        TopBar(
                            title = title,
                            imageUrl = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small",
                            onMenuClick = { scope.launch { state.drawerState.open() } },
                            navController = navController
                        )
                    },
                    drawerContent = {
                        NavigationDrawer(
                            navController = navController,
                            onMenuClick = { scope.launch { state.drawerState.close() } }
                        )
                    },
                ) {
                    MyAppNavHost(
                        navController = navController,
                        startDestination = Screens.Exercises.route
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

