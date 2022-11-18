package com.example.fitpal_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.ui.navigation.AuthNavHost
import com.example.fitpal_android.ui.screens.appContent.mainScreen.MainScreen
import com.example.fitpal_android.ui.theme.FitpalandroidTheme
import com.example.fitpal_android.util.getViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter") // TODO: revisar si esto es relevante
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {
                val scaffoldState: ScaffoldState = rememberScaffoldState()
                val mainActivityViewModel = viewModel<MainActivityViewModel>(factory = getViewModelFactory())
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {

                    if (mainActivityViewModel.state.isAuthenticated) {
                        MainScreen(
                            scaffoldState = scaffoldState,
                            onLoggedOut = { mainActivityViewModel.loggedOut() }
                        )
                    } else {
                        AuthNavHost(
                            scaffoldState = scaffoldState,
                            onAuthentication = { mainActivityViewModel.loggedIn() }
                        )
                    }
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

