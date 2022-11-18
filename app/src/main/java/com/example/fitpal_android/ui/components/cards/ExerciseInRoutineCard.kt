package com.example.fitpal_android.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitpal_android.R

@Composable
fun ExerciseInRoutineCard(exerciseName: String, reps: Int, time: String?, modifier: Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Exercise name
                Text(
                    text = exerciseName,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                )

                if(reps==0){
                    Text(text= (stringResource(R.string.time_routine)) + time, style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp))
                }
                else{
                    Text(text= reps.toString() + stringResource(R.string.reps_exercise), style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp))
                }

            }
        }

    }
}