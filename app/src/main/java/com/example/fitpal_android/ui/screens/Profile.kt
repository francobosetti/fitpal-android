package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun Profile() {

    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopBar(
                title = "Profile",
                imageUrl = "https://st.depositphotos.com/1008939/2240/i/950/depositphotos_22408839-stock-photo-serious.jpg",
                onMenuClick = { scope.launch { state.drawerState.open() } })
        },
        drawerContent = {
            NavigationDrawer(
                selectedScreen = 3,
                onMenuClick = { scope.launch { state.drawerState.close() } },
                onItemClick = { /* TODO */}
            )
        },
    ) { padding ->
        Surface(color = MaterialTheme.colors.background) {
            Modifier.padding(padding)
            Text(text = "Profile")
        }
    }
}