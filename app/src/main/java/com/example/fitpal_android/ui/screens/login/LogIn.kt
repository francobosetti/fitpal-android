package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.screens.login.LoginFormEvent
import com.example.fitpal_android.ui.screens.login.LoginViewModel
import com.example.fitpal_android.ui.theme.Black000
import com.example.fitpal_android.ui.theme.Gray400
import com.example.fitpal_android.ui.theme.Orange500
import kotlinx.coroutines.flow.collect

@Composable
fun LogIn(onButtonClicked: () -> Unit){

    val viewModel = viewModel<LoginViewModel>()
    val formState = viewModel.formState
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect {
            event -> when(event) {
                is ValidationEvent.Success -> {
                    onButtonClicked()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray400),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.fitpal_horizontallogo),
            contentDescription = "Fitpal Logo",
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(70.dp))

        Text(modifier = Modifier.padding(20.dp),text = stringResource(R.string.welcome_message), style = TextStyle(fontSize = 40.sp, textAlign = TextAlign.Center), color= Color.White)

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            colors= TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                focusedIndicatorColor = Orange500,
                focusedLabelColor = Orange500,
                cursorColor = Orange500),
            label = { Text(text = stringResource(R.string.log_in_username)) },
            value = formState.email,
            onValueChange = { viewModel.onEvent(LoginFormEvent.EmailChanged(it)) })
        if(formState.emailError != null) {
            Text(
                text = formState.emailError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            colors = TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                focusedIndicatorColor = Orange500,
                focusedLabelColor = Orange500,
           cursorColor = Orange500),
            label = { Text(text = stringResource(R.string.log_in_password)) },
            value = formState.password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { viewModel.onEvent(LoginFormEvent.Submit) },
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange500)
            ) {
                Text(
                    text = stringResource(R.string.log_in_button),
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    color= Color.White
                )
            }
        }
    }
}
