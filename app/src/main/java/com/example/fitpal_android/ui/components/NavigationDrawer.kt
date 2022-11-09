package com.example.fitpal_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitpal_android.R
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.screens.Profile

@Composable
fun NavigationDrawer(navController: NavController, onMenuClick: () -> Unit) {



    val menuItems = listOf(
        Screens.Exercises,
        Screens.Routines,
        Screens.ExploreRoutines,
        Screens.Profile
    )

    return Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
    ) {
        // Drawer toggle button
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { onMenuClick() }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
            Image(
                painter = painterResource(id = R.drawable.fitpal_horizontallogo),
                contentDescription = "Fitpal Logo",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Drawer items if they are selected highlight them
        menuItems.forEach { menuItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(vertical = 16.dp)
            ) {
                // Icon
                Icon(
                    painter = painterResource(id = menuItem.icon),
                    contentDescription = menuItem.iconDesc
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Text
                Text(
                    text = menuItem.title,
                    color = if (menuItem.isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

