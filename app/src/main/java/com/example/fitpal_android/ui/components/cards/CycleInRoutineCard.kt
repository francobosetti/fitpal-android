package com.example.fitpal_android.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpal_android.R
import com.example.fitpal_android.data.model.CycleExercise
import com.example.fitpal_android.data.model.Exercise

@Composable
fun CycleInRoutineCard(cycleIndex : Int, cycleName : String, exercises : List<CycleExercise>, modifier: Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = modifier
    ) {
        Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(text = (cycleIndex + 1).toString() + ": ",
                        modifier = Modifier
                            .padding(end = 10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 45.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = Color.White
                    )
                    Text(text = cycleName,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = Color.White
                    )}
            for (ex in exercises) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ){
                    // Exercise name
                    Text(
                        text = ex.exercise.name + ": ",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(start = 12.dp, bottom = 8.dp)
                    )
                    if(ex.repetitions==0){
                        Text(text= ex.duration.toString() + (stringResource(R.string.seconds)),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.padding(start = 12.dp, bottom = 8.dp))
                    }
                    else{
                        Text(text= ex.repetitions.toString() + stringResource(R.string.reps_exercise), style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.padding(start = 12.dp, bottom = 8.dp))
                    }

                }
            }
        }
    }
}