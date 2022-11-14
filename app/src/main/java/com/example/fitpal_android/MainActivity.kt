package com.example.fitpal_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.navigation.AuthNavHost
import com.example.fitpal_android.ui.screens.appContent.mainScreen.MainScreen
import com.example.fitpal_android.ui.theme.FitpalandroidTheme
import com.example.fitpal_android.util.getViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {

                val mainActivityViewModel = viewModel<MainActivityViewModel>(factory = getViewModelFactory())
                if (mainActivityViewModel.state.isAuthenticated) {
                    MainScreen(onLoggedOut = { mainActivityViewModel.loggedOut() })
                } else {
                    AuthNavHost(onAuthentication = { mainActivityViewModel.loggedIn() })
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

