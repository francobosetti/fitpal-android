package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.theme.Black000
import com.example.fitpal_android.ui.theme.Gray400
import com.example.fitpal_android.ui.theme.Orange500

@Composable
fun LogIn(onButtonClicked: () -> Unit){
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
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(modifier = Modifier.padding(20.dp),text = stringResource(R.string.welcome_message), style = TextStyle(fontSize = 40.sp, textAlign = TextAlign.Center), color= Color.White)

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            colors= TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                focusedIndicatorColor = Orange500,
                focusedLabelColor = Orange500,
                cursorColor = Orange500),
            label = { Text(text = stringResource(R.string.log_in_username)) },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            colors = TextFieldDefaults.textFieldColors(unfocusedLabelColor = Black000,
                focusedIndicatorColor = Orange500,
                focusedLabelColor = Orange500,
           cursorColor = Orange500),
            label = { Text(text = stringResource(R.string.log_in_password)) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { onButtonClicked() },
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
