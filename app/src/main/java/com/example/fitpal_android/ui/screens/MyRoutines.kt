package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import com.example.fitpal_android.ui.components.cards.RoutineCard
import kotlinx.coroutines.launch

@Composable
fun MyRoutines() {

    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val routines = listOf(
        MyRoutine(
            name = "Chest",
            imageUrl = "https://pbs.twimg.com/media/FfoC1MmWAAkViRz?format=jpg&name=small",
            description = "A routine that will make your chest grow",
            rating = 4.5,
        ),
        MyRoutine(
            name = "Legs",
            imageUrl = "https://pbs.twimg.com/media/Fft9e-FXgAIzIfv?format=jpg&name=small",
            description = "A routine that will make your legs grow",
            rating = 3.5,
        ),
        MyRoutine(
            name = "Back",
            imageUrl = "https://pbs.twimg.com/media/Fft_ZgcXwAEuS2-?format=jpg&name=small",
            description = "A routine that will make your back grow",
            rating = 2.5,
        ),
        MyRoutine(
            name = "Arms",
            imageUrl = "https://pbs.twimg.com/media/Ffzc9nAXEAMdh8T?format=jpg&name=small",
            description = "A routine that will make your arms grow",
            rating = 1.5,
        ),
        MyRoutine(
            name = "Shoulders",
            imageUrl = "https://pbs.twimg.com/media/Ffi2TERXoAAzFA1?format=jpg&name=4096x4096",
            description = "A routine that will make your shoulders grow",
            rating = 0.5,
        ),
    )

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopBar(
                title = "My Routines",
                imageUrl = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small",
                onMenuClick = { scope.launch { state.drawerState.open() } })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Routine",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        drawerContent = {
            NavigationDrawer(
                selectedScreen = 1,
                onMenuClick = { scope.launch { state.drawerState.close() } },
                onItemClick = { /* TODO */ }
            )
        },
    ) { padding ->
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(routines.size) {
                    RoutineCard(
                        name = routines[it].name,
                        imageUrl = routines[it].imageUrl,
                        description = routines[it].description,
                        rating = routines[it].rating
                    )
                }
            }
        }
    }
}

// TODO: sacar esto cuando tengamos backend
private class MyRoutine(
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double
)