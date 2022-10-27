package com.example.fitpal_android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.R

@Composable
fun NavigationDrawer(onMenuClick : () -> Unit = {}, selectedScreen : Number) {


    Column(
        modifier = Modifier.padding(16.dp).fillMaxHeight()
    ) {
        // Drawer toggle button
        IconButton(onClick = { onMenuClick() }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }

        // Drawer items if they are selected highlight them

        // Exercises
        Row {
            // Icon
            Icon(painter = painterResource(id = R.drawable.ic_baseline_self_improvement_24), contentDescription = "Exercises")
            // Text
            Text(text = "Exercises")
        }

        // Routines
        Row {
            // Icon
            Icon(painter = painterResource(id = R.drawable.ic_baseline_sports_gymnastics_24), contentDescription = "Routines")
            // Text
            Text(text = "Routines")
        }

        // Explore
        Row {
            // Icon
            Icon(painter = painterResource(id = R.drawable.ic_baseline_explore_24), contentDescription = "Explore")
            // Text
            Text(text = "Explore")
        }

        // Profile
        Row {
            // Icon
            Icon(Icons.Default.Person, contentDescription = "Profile")
            // Text
            Text(text = "Profile")
        }

    }
}