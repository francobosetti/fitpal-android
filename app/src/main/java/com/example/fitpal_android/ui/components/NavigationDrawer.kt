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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitpal_android.R
import com.example.fitpal_android.Screens
import com.example.fitpal_android.data.repository.DEPRECATED.UserRepository
import com.example.fitpal_android.ui.theme.White100

@Composable
fun NavigationDrawer(navController: NavController, onMenuClick: () -> Unit) {


    // Items for nav menu
    val menuItems = listOf(
        Screens.Exercises,
        Screens.Routines,
        Screens.FavoriteRoutine,
        Screens.ExploreRoutines,
        Screens.Profile
    )

    // Current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


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
                            launchSingleTop = true
                            restoreState = true
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

                // If we are in a secondary screen we have to show the closest main screen in orange
                val notInMenuItem = !menuItems.any{ item -> currentRoute == item.route }
                val closestMenuItem = navController.backQueue.lastOrNull { entry -> menuItems.any{ item -> entry.destination.route == item.route } }?.destination?.route

                // Text
                Text(
                    text = stringResource(menuItem.title),
                    color = if ( currentRoute == menuItem.route || (notInMenuItem && closestMenuItem == menuItem.route )) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                UserRepository().logOut()
            },
            Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.log_out_button), color = White100)
        }
    }
}

