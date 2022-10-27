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
import com.example.fitpal_android.R

@Composable
fun NavigationDrawer(onMenuClick: () -> Unit, selectedScreen: Int, onItemClick: () -> Unit) {


    val menuItems = listOf(
        MenuItem(
            title = "Exercises",
            iconId = R.drawable.ic_baseline_self_improvement_24,
            iconContentDescription = "Exercises",
            isSelected = selectedScreen == 0
        ),
        MenuItem(
            title = "Routines",
            iconId = R.drawable.ic_baseline_sports_gymnastics_24,
            iconContentDescription = "Routines",
            isSelected = selectedScreen == 1
        ),
        MenuItem(
            title = "Explore Routines",
            iconId = R.drawable.ic_baseline_explore_24,
            iconContentDescription = "Explore Routines",
            isSelected = selectedScreen == 2
        ),
        MenuItem(
            title = "Profile",
            iconId = R.drawable.ic_baseline_person_24,
            iconContentDescription = "Profile",
            isSelected = selectedScreen == 3
        ),
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
                    .clickable { /* TODO */ }
                    .padding(vertical = 16.dp)
            ) {
                // Icon
                Icon(
                    painter = painterResource(id = menuItem.iconId),
                    contentDescription = menuItem.iconContentDescription
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

private class MenuItem(
    var title: String,
    var iconId: Int,
    var iconContentDescription: String,
    var isSelected: Boolean
) {}