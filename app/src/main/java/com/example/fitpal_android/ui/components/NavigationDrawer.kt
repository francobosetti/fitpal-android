package com.example.fitpal_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitpal_android.R
import com.example.fitpal_android.Screens
import com.example.fitpal_android.ui.theme.White100

@Composable
fun NavigationDrawer(navController: NavController, onMenuClick: () -> Unit) {


    // Items for nav menu
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
                    .clickable {
                        navController.navigate(menuItem.route) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    // TODO: determinar si queremos que esto se limpie o no
                                    // Si es true, si estoy en una detailed routine y voy al profile, cuando vuelvo sigo en la detailed routine
                                    // Si es false cuando vuelvo aparece la lista de rutinas
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        onMenuClick()
                    }
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
                    color = if (navController.backQueue.any { entry -> entry.destination.route == menuItem.route }) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
        Button(onClick = { navController.navigate(Screens.LogIn.route);onMenuClick()},
        Modifier.fillMaxWidth()) {
            Text(text = "Log Out", color = White100)
        }
    }
}

