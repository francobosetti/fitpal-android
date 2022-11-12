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
import com.example.fitpal_android.ui.screens.authentication.verify.Verify

@Composable
fun AuthNavHost(
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = Screens.LogIn.route) {

        // Login
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

        // Sign Up
        composable(Screens.SignUp.route) {
            SignUp(
                onButtonClicked = {
                    navController.navigate(Screens.Verify.route) {
                        popUpTo(0)
                    }
                },

                onLinkClicked = {
                    navController.navigate(Screens.LogIn.route) {
                        popUpTo(Screens.LogIn.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Verify
        composable(Screens.Verify.route) {
            Verify(
                onButtonClicked = { UserRepository().logIn() },
            )
        }

    }
}