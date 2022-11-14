package com.example.fitpal_android.ui.screens.authentication.login

import android.view.KeyEvent.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.ProgressButton
import com.example.fitpal_android.ui.screens.ValidationEvent
import com.example.fitpal_android.ui.theme.Black000
import com.example.fitpal_android.ui.theme.Gray400
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.ui.theme.White100
import com.example.fitpal_android.util.getViewModelFactory


@Composable
fun LogIn(onAuthentication: () -> Unit, onLinkClicked: () -> Unit){

    val viewModel = viewModel<LoginViewModel>(factory = getViewModelFactory())
    val formState = viewModel.loginFormState
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val noEnterNoTabRegex = Regex("^[^\\t\\n]*\$")
    val scrollState= rememberScrollState()

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect {
            event -> when(event) {
                is ValidationEvent.Success -> {
                    onAuthentication()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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


        Column {
            TextField(
                colors= TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                    focusedIndicatorColor = Orange500,
                    focusedLabelColor = Orange500,
                    cursorColor = Orange500),
                label = { Text(text = stringResource(R.string.log_in_email)) },
                value = formState.email,
                onValueChange = {
                    if (it.matches(noEnterNoTabRegex)) {
                        viewModel.onEvent(LoginFormEvent.EmailChanged(it))
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), //TODO: elegir la accion
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                modifier = Modifier.onKeyEvent {
                    if(it.nativeKeyEvent.keyCode == KEYCODE_ENTER || it.nativeKeyEvent.keyCode == KEYCODE_TAB) {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                    false
                }
            )
            formState.emailError?.let {
                Text(
                    text = formState.emailError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column {
            TextField(
                colors = TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                    focusedIndicatorColor = Orange500,
                    focusedLabelColor = Orange500,
                    cursorColor = Orange500),
                label = { Text(text = stringResource(R.string.log_in_password)) },
                value = formState.password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    if (it.matches(noEnterNoTabRegex)) {
                        viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                    }
                },
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus()}
                ),
                modifier = Modifier.onKeyEvent {
                    if(it.nativeKeyEvent.keyCode == KEYCODE_ENTER || it.nativeKeyEvent.keyCode == KEYCODE_TAB) {
                        focusManager.clearFocus()
                    }
                    false
                }
            )
        }



        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            ProgressButton(
                onClick = { viewModel.onEvent(LoginFormEvent.Login)},
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                loading = formState.loading,
                enabled = !formState.loading, //TODO VER EL ENABLED EN VIEW MODEL
                color = Orange500,
                progressColor = Color.White
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
        Box(modifier = Modifier.fillMaxSize()) {
            ClickableText(
                text = AnnotatedString(stringResource(R.string.sing_up_here)),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = { onLinkClicked()},
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Orange500
                )
            )
        }
    }
}
