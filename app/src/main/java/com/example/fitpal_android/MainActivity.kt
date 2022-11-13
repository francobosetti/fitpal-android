package com.example.fitpal_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitpal_android.data.repository.DEPRECATED.UserRepository
import com.example.fitpal_android.ui.navigation.AuthNavHost
import com.example.fitpal_android.ui.screens.appContent.MainScreen
import com.example.fitpal_android.ui.theme.FitpalandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {


                if (UserRepository().isUserLoggedIn()) {
                    MainScreen()
                } else {
                    AuthNavHost()
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

