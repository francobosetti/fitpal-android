package com.example.fitpal_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitpal_android.ui.screens.Exercises
import com.example.fitpal_android.ui.screens.ExploreRoutines
import com.example.fitpal_android.ui.screens.MyRoutines
import com.example.fitpal_android.ui.screens.Profile
import com.example.fitpal_android.ui.theme.FitpalandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpalandroidTheme {
                // A surface container using the 'background' color from the theme
                Exercises()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitpalandroidTheme {
        Exercises()
    }
}