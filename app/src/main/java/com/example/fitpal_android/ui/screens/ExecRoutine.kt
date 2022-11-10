package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.Exercise
import com.example.fitpal_android.data.repository.MyRoutine
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.ui.theme.White100


@Composable
fun ExecRoutine(
    routineId: Int?,
    viewModel : ExercisesViewModel = ExercisesViewModel(routineId)
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row{
                Text(
                    text = viewModel.getCurrentExercise().name,
                    modifier = Modifier
                        .padding(10.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    ),
                    color= Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressBar(
                    progress = 0.7f,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                    onClick = { viewModel.previousExercise() },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(100.dp)
                ) {
                    Text(text = "Previous", color = White100)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                    onClick = { viewModel.nextExercise() },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(100.dp)
                ) {
                    Text(text = "Next", color = White100)
                }
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Orange500,
    strokeWidth: Dp = 4.dp,
    animationDuration: Int = 1000,
    indeterminate: Boolean = false,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val progressAngle by animateFloatAsState(
        targetValue = if (indeterminate) animatedProgress else progress * 360,
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = LinearEasing
        )
    )

    Canvas(modifier = modifier) {
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = progressAngle,
            useCenter = false,
            style = Stroke(strokeWidth.toPx())
        )
    }
}

class ExercisesViewModel( private val routineId: Int? ) : ViewModel() {
    private var uiState by mutableStateOf(ExercisesUiState(
        routine = RoutineRepository().getMyRoutines().find { it.id == routineId },
    ))

    fun getCurrentExercise(): Exercise {
        return uiState.exercises[uiState.currentExerciseIndex]
    }

    fun nextExercise() {
        if(uiState.currentExerciseIndex < uiState.exercises.size - 1) {
            uiState = uiState.copy(currentExerciseIndex = uiState.currentExerciseIndex + 1)
        }
    }

    fun previousExercise() {
        if (uiState.currentExerciseIndex > 0) {
            uiState = uiState.copy(
                currentExerciseIndex = uiState.currentExerciseIndex - 1
            )
        }
    }
}

data class ExercisesUiState(
    val routine: MyRoutine?,
    val exercises: List<Exercise> = RoutineRepository().getMyRoutines().find { it.id == routine?.id }?.exercises ?: emptyList(),
    var currentExerciseIndex : Int = 0,
)