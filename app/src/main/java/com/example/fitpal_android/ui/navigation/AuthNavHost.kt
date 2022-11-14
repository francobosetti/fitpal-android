package com.example.fitpal_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.screens.authentication.signup.SignUp
import com.example.fitpal_android.ui.screens.authentication.login.LogIn
import com.example.fitpal_android.ui.screens.authentication.verify.Verify

@Composable
fun AuthNavHost(
    navController: NavHostController = rememberNavController(),
    onAuthentication: () -> Unit,
) {

    NavHost(navController = navController, startDestination = Screens.LogIn.route) {

        // Login
        composable(Screens.LogIn.route) {
            LogIn(
                onAuthentication = onAuthentication,
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
                onSignUpClicked = { email, password ->
                    navController.navigate("Verify/$email/$password") {
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
        composable(Screens.Verify.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },
                navArgument("password") {
                    type = NavType.StringType
                }
            )
        ) {
            Verify(
                onAuthentication = onAuthentication,
                email = it.arguments?.getString("email") ?: "",
                password = it.arguments?.getString("password") ?: ""
            )
        }

    }
}