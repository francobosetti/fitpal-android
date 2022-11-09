package com.example.fitpal_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold() {
                MyAppNavHost()
            }

            FitpalandroidTheme {
                // A surface container using the 'background' color from the theme

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