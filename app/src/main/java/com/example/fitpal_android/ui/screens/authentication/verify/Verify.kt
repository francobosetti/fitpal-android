package com.example.fitpal_android.ui.screens.authentication.verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.screens.ValidationEvent
import com.example.fitpal_android.ui.screens.authentication.login.LoginFormEvent
import com.example.fitpal_android.ui.screens.authentication.login.LoginViewModel
import com.example.fitpal_android.ui.theme.Black000
import com.example.fitpal_android.ui.theme.Gray400
import com.example.fitpal_android.ui.theme.Orange500

@Composable
fun Verify(onButtonClicked: () -> Unit){

    val viewModel = viewModel<VerifyViewModel>()
    val verfyFormState = viewModel.verfyFormState
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect{
            event -> when(event) {
                is ValidationEvent.Success -> {
                    onButtonClicked() // THIS SHOULD SEND TO HOMEPAGE
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

        // TODO: add spanish version
        Text(modifier = Modifier.padding(20.dp),text = "Verify", style = TextStyle(fontSize = 40.sp, textAlign = TextAlign.Center), color= Color.White)

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            colors= TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                focusedIndicatorColor = Orange500,
                focusedLabelColor = Orange500,
                cursorColor = Orange500
            ),
            label = { Text(text = stringResource(R.string.log_in_username)) },
            value = verfyFormState.verificationCode,
            onValueChange = { viewModel.onEvent(VerifyFormEvent.CodeChanged(it)) })

        if(verfyFormState.verificationCodeError != null) {
            Text(
                text = verfyFormState.verificationCodeError,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { viewModel.onEvent(VerifyFormEvent.Submit) },
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange500)
            ) {
                Text(
                    text = "Verify", //TODO: add spanish version
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    color= Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { viewModel.onEvent(VerifyFormEvent.ResendCode) },
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange500)
            ) {
                Text(
                    text = "Resend Code", //TODO: add spanish version
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