package com.example.fitpal_android.ui.screens.appContent.profile

import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.ProgressButton
import com.example.fitpal_android.ui.screens.ValidationEvent
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.util.getViewModelFactory

// TODO: make resolve enter inside text field
@Composable
fun Profile(
    onProfileUpdate: () -> Unit,
) {

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(8.dp)) {

        val viewModel = viewModel<ProfileViewModel>(factory = getViewModelFactory())

        val profileState = viewModel.profileState
        val profileFormState = viewModel.profileFormState
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val noEnterNoTabRegex = Regex("^[^\\t\\n]*\$")

        LaunchedEffect(key1 = context) {
            viewModel.validationEvents.collect {
                event -> when(event) {
                    is ValidationEvent.Success -> {
                        onProfileUpdate()
                    }
                }
            }
        }

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
                        model = profileState.avatarUrl,
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .size(80.dp)
                    )

                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        // Name
                        Text(
                            text = "${profileState.firstname} ${profileState.lastname}",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        // Email
                        Text(
                            text = profileState.email,
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
                        value = profileFormState.firstname,
                        isError = profileFormState.firstnameError != null,
                        label = { Text(stringResource(R.string.profile_first_name)) },
                        onValueChange = {
                            if (it.matches(noEnterNoTabRegex)) {
                                viewModel.onEvent(ProfileFormEvent.FirstnameChanged(it))
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), //TODO: elegir la accion
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier.onKeyEvent {
                            if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER || it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                            false
                        }.padding(8.dp).fillMaxWidth(),
                    )
                    profileFormState.firstnameError?.let {
                        Text(
                            text = profileFormState.firstnameError,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    // Last name
                    OutlinedTextField(
                        value = profileFormState.lastname,
                        isError = profileFormState.lastnameError != null,
                        label = { Text(stringResource(R.string.profile_last_name)) },
                        onValueChange = {
                            if (it.matches(noEnterNoTabRegex)) {
                                viewModel.onEvent(ProfileFormEvent.LastnameChanged(it))
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), //TODO: elegir la accion
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier.onKeyEvent {
                            if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER || it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                            false
                        }.padding(8.dp).fillMaxWidth(),
                    )
                    profileFormState.lastnameError?.let {
                        Text(
                            text = profileFormState.lastnameError,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    // Email
                    OutlinedTextField(
                        value = profileState.email,
                        // Value cant be changed
                        onValueChange = { /*TODO*/ }, //
                        label = { Text(stringResource(R.string.profile_email)) },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        enabled = false
                    )

                    // Profile picture
                    OutlinedTextField(
                        value = profileFormState.avatarUrl,
                        isError = profileFormState.avatarUrlError != null,
                        label = { Text(stringResource(R.string.Profile_pic)) },
                        onValueChange = {
                            if (it.matches(noEnterNoTabRegex)) {
                                viewModel.onEvent(ProfileFormEvent.AvatarUrlChanged(it))
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri, imeAction = ImeAction.Next), //TODO: elegir la accion
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.clearFocus() }
                        ),
                        modifier = Modifier.onKeyEvent {
                            if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER || it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                                focusManager.clearFocus()
                            }
                            false
                        }.padding(8.dp).fillMaxWidth(),
                    )
                    profileFormState.avatarUrlError?.let {
                        Text(
                            text = profileFormState.avatarUrlError,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }


                //  ----------------------- Buttons -----------------------
                // Edit profile button
                ProgressButton(
                    onClick = { viewModel.onEvent(ProfileFormEvent.EditProfile)},
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    loading = profileFormState.editLoading,
                    enabled = !profileFormState.editLoading,
                    color = Orange500,
                    progressColor = Color.White
                ) {
                    Text(stringResource(R.string.edit_profile_button))
                }
            }
        }

        // TODO: revisar si se puede seguir usando esto
        /*
        ProfileForm(
            firstname = currentUser.firstname,
            lastname = currentUser.lastname,
            email = currentUser.email,
            avatarUrl = currentUser.avatarUrl,
        )
         */
    }

}

/*
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
                    label = { Text(stringResource(R.string.profile_first_name)) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                // Last name
                OutlinedTextField(
                    value = lastname,
                    onValueChange = { /*TODO*/ },
                    label = { Text(stringResource(R.string.profile_last_name)) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { /*TODO*/ },
                    label = { Text(stringResource(R.string.profile_email)) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    enabled = false
                )

                // Profile picture
                OutlinedTextField(
                    value = avatarUrl,
                    onValueChange = { /*TODO*/ },
                    label = { Text(stringResource(R.string.Profile_pic)) },
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
                Text(stringResource(R.string.edit_profile_button))
            }
        }
    }
}
 */