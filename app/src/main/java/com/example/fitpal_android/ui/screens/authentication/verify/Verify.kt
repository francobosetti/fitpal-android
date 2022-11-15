package com.example.fitpal_android.ui.screens.authentication.verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.ProgressButton
import com.example.fitpal_android.ui.screens.ValidationEvent
import com.example.fitpal_android.ui.theme.Black000
import com.example.fitpal_android.ui.theme.Gray400
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun Verify(onAuthentication: () -> Unit, email: String, password: String) {

    val viewModel = viewModel<VerifyViewModel>(factory = getViewModelFactory())
    val verifyFormState = viewModel.verifyFormState
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    onAuthentication()
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

        Text(
            modifier = Modifier.padding(20.dp),
            text = stringResource(R.string.verify),
            style = TextStyle(fontSize = 40.sp, textAlign = TextAlign.Center),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column {
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Black000,
                    focusedIndicatorColor = Orange500,
                    focusedLabelColor = Orange500,
                    cursorColor = Orange500
                ),
                label = { Text(text = stringResource(R.string.verification_code)) },
                value = verifyFormState.verificationCode,
                onValueChange = { viewModel.onEvent(VerifyFormEvent.CodeChanged(it)) })

            verifyFormState.verificationCodeError?.let {
                Text(
                    text = verifyFormState.verificationCodeError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            ProgressButton(
                onClick = { viewModel.onEvent(VerifyFormEvent.VerifyCode, email = email, password = password)},
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                loading= verifyFormState.verifyLoading,
                enabled = !verifyFormState.verifyLoading && !verifyFormState.resendLoading,
                color = Orange500,
                progressColor = Color.White
            ) {
                Text(
                    text = stringResource(R.string.verify),
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            ProgressButton(
                onClick = { viewModel.onEvent(VerifyFormEvent.ResendCode, email = email)},
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                loading= verifyFormState.resendLoading,
                enabled = !verifyFormState.resendLoading && !verifyFormState.verifyLoading,
                color = Orange500,
                progressColor = Color.White

            ) {
                Text(
                    text = stringResource(R.string.resend_code),
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.White
                )
            }
        }
    }
}