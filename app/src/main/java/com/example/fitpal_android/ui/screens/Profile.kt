package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitpal_android.ui.components.NavigationDrawer
import com.example.fitpal_android.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun Profile(

) {

    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopBar(
                title = "Profile",
                imageUrl = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small",
                onMenuClick = { scope.launch { state.drawerState.open() } })
        },
        drawerContent = {
            NavigationDrawer(
                selectedScreen = 3,
                onMenuClick = { scope.launch { state.drawerState.close() } },
                onItemClick = { /* TODO */ }
            )
        },
    ) { padding ->
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(padding)) {
            // Form with profile info
            ProfileForm(
                firstName = "Faker",
                lastName = "Faker",
                email = "faker@gmail.com",
                profilePicture = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small"
            )
        }
    }
}

@Composable
fun ProfileForm(firstName: String, lastName: String, email: String, profilePicture: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column {
            // Profile picture & name/email
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Show profile picture
                AsyncImage(
                    model = profilePicture,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(
                            CircleShape /* TODO: hacer q sea un circulo */
                        )
                        .size(100.dp)
                )

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    // Name
                    Text(
                        text = "$firstName $lastName",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    // Email
                    Text(
                        text = email,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            //  ----------------------- Text fields -----------------------

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // First name
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { /*TODO*/ },
                    label = { Text("First name") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                // Last name
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { /*TODO*/ },
                    label = { Text("Last name") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { /*TODO*/ },
                    label = { Text("Email") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    enabled = false
                )

                // Profile picture
                OutlinedTextField(
                    value = profilePicture,
                    onValueChange = { /*TODO*/ },
                    label = { Text("Profile picture") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }


            //  ----------------------- Buttons -----------------------
            // Edit profile button
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Edit profile")
            }
        }
    }
}