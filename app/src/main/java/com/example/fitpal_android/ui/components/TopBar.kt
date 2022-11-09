package com.example.fitpal_android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitpal_android.Screens

@Composable
fun TopBar(
    title: String,
    imageUrl: String,
    onMenuClick: () -> Unit = {},
    navController: NavController
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Screens.Profile.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }, modifier = Modifier
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Profile",
                    modifier = Modifier.clip( /* TODO: hacer que la forma sea circular y no ovalada */
                        CircleShape
                    ),
                )
            }
        }
    )
}