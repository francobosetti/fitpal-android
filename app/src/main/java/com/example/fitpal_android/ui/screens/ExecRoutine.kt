package com.example.fitpal_android.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.fitpal_android.ui.MyRoutine

@Composable
fun ExecRoutine(
    routineId: Int?,
) {
    Surface(color = MaterialTheme.colors.background) {
        Text(text ="ExecRoutine, Routine Id: $routineId")
    }

}