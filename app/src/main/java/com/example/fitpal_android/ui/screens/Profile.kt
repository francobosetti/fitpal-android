package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitpal_android.data.repository.UserRepository

@Composable
fun Profile() {

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(8.dp)) {
        // Form with profile info
        ProfileForm(
            firstname = UserRepository().getCurrentUser().firstname,
            lastname = UserRepository().getCurrentUser().lastname,
            email = UserRepository().getCurrentUser().email,
            avatarUrl = UserRepository().getCurrentUser().avatarUrl,
        )
    }

}

@Composable
fun ProfileForm(firstname: String, lastname: String, email: String, avatarUrl: String) {
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
                    model = avatarUrl,
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
                        text = "$firstname $lastname",
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
                    value = firstname,
                    onValueChange = { /*TODO*/ },
                    label = { Text("First name") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                // Last name
                OutlinedTextField(
                    value = lastname,
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
                    value = avatarUrl,
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