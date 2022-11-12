package com.example.fitpal_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitpal_android.Screens
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.ui.screens.authentication.signup.SignUp
import com.example.fitpal_android.ui.screens.authentication.login.LogIn

@Composable
fun AuthNavHost(
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = Screens.LogIn.route) {
        composable(Screens.LogIn.route) {
            LogIn(
                onButtonClicked = { UserRepository().logIn() },
                onLinkClicked = {
                    navController.navigate(Screens.SignUp.route) {
                        popUpTo(Screens.LogIn.route)
                    }
                }
            )
        }
        composable(Screens.SignUp.route) {
            SignUp(
                onButtonClicked = { UserRepository().logIn() },
                onLinkClicked = {
                    navController.navigate(Screens.LogIn.route) {
                        popUpTo(Screens.LogIn.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}