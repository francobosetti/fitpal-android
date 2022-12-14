package com.example.fitpal_android.ui.screens.appContent.execRoutine

import androidx.compose.foundation.layout.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.ui.components.cards.CycleInRoutineCard
import com.example.fitpal_android.ui.theme.Orange500
import com.example.fitpal_android.util.getViewModelFactory
import java.util.*


@Composable
fun ExecRoutine(
    routineId: Int?,
    onBackPressed: () -> Unit,
    viewModel: ExecRoutineViewModel = viewModel(factory = getViewModelFactory(routineId))
) {
    if (viewModel.isDetailedMode()) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.cycle) + ' ' + (viewModel.getCurrentCycleIndex() + 1) + ": " + viewModel.getCurrentCycleName(),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = Color.White
                    )
                }
                Row {
                    Text(
                        text = viewModel.getCurrentExerciseName(),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (viewModel.getReps() == 0)
                        CountDownView(viewModel = viewModel)
                    else {
                        Text(
                            text = viewModel.getReps()
                                .toString() + stringResource(R.string.reps_exercise),
                            color = Color.White,
                            //fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            style = MaterialTheme.typography.h2,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                    if (viewModel.getCurrentExerciseIndex() > 0 || viewModel.getCurrentCycleIndex() > 0) {
                        Button(
                            onClick = { viewModel.previousExercise() },
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .height(50.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Orange500)
                        ) {
                            Text(
                                text = stringResource(R.string.previous_button),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                ),
                                color = Color.White
                            )
                        }
                    }
                    if (viewModel.getCurrentExerciseIndex() < viewModel.getExercisesSize() - 1) {
                        Button(
                            onClick = { viewModel.nextExercise() },
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .height(50.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                        ) {
                            Text(
                                text = stringResource(R.string.next_button),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                ),
                                color = Color.White
                            )
                        }
                    } else if (viewModel.getCurrentCycleIndex() < viewModel.getCyclesSize() - 1) {
                        Button(
                            onClick = { viewModel.nextCycle() },
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .height(50.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                        ) {
                            Text(
                                text = stringResource(R.string.next_cycle),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                ),
                                color = Color.White
                            )
                        }
                    } else {
                        Button(
                            onClick = { onBackPressed() },
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .height(50.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
                        ) {
                            Text(
                                text = stringResource(R.string.finish_button),
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
        }
    } else {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.getRoutineName()?.let {
                Text(
                    text = it.uppercase(),
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            for ((index, cycle) in viewModel.getCycles().withIndex()) {
                CycleInRoutineCard(
                    cycleIndex = index,
                    cycleName = cycle.name,
                    exercises = cycle.exercises,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Divider(color = Color.White, thickness = 1.dp)
            }
            Button(
                onClick = { onBackPressed() },
                modifier = Modifier
                    .padding(15.dp)
                    .height(50.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
            ) {
                Text(
                    text = stringResource(R.string.finish_button),
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

@Composable
fun CountDownIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    time: String,
    size: Int,
    stroke: Int
) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Column(modifier = modifier) {
        Box {

            CircularProgressIndicatorBackGround(
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = colorResource(R.color.secondary),
                stroke
            )

            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = colorResource(R.color.primary),
                strokeWidth = stroke.dp,
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = time,
                    color = Color.White,
                    //fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CircularProgressIndicatorBackGround(
    modifier: Modifier = Modifier,
    color: Color,
    stroke: Int
) {
    val style = with(LocalDensity.current) { Stroke(stroke.dp.toPx()) }

    Canvas(modifier = modifier, onDraw = {

        val innerRadius = (size.minDimension - style.width) / 2

        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 360f,
            topLeft = Offset(
                (size / 2.0f).width - innerRadius,
                (size / 2.0f).height - innerRadius
            ),
            size = Size(innerRadius * 2, innerRadius * 2),
            useCenter = false,
            style = style
        )

    })
}

@Composable
fun CountDownButton(
    text: String,
    optionSelected: () -> Unit
) {
    Button(
        onClick = {
            optionSelected()
        },
        modifier =
        Modifier
            .padding(start = 10.dp, end = 10.dp)
            .height(70.dp)
            .width(150.dp),

        shape = RoundedCornerShape(25.dp),

        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.primary),
            contentColor = colorResource(id = R.color.primary),
        ),

        )

    {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.White,
            //fontFamily = FontFamily(Font(R.font.poppins_semibold))
        )
    }
}

@Composable
fun CountDownView(viewModel: ExecRoutineViewModel) {
    CountDownScreen(
        time = viewModel.getFormatedCurrentTime(),
        progress = viewModel.getProgress(),
        isPlaying = viewModel.getIsPlaying(),
        isPaused = viewModel.getIsPaused(),
        start = {
            viewModel.startTimer()
        },
        stop = {
            viewModel.stopTimer()
        },
        pause = {
            viewModel.pauseTimer()
        },
    )
}

@Composable
fun CountDownScreen(
    time: String,
    progress: Float,
    isPlaying: Boolean,
    isPaused: Boolean,
    start: () -> Unit,
    stop: () -> Unit,
    pause: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CountDownIndicator(
            Modifier.padding(top = 50.dp, bottom = 50.dp),
            progress = progress,
            time = time,
            size = 250,
            stroke = 12
        )

        Row {
            if (isPaused)
                CountDownButton(
                    text = stringResource(R.string.resume_exercise),
                    optionSelected = start
                )
            else if (isPlaying)
                CountDownButton(
                    text = stringResource(R.string.pause_exercise),
                    optionSelected = pause
                )

            if (isPaused || isPlaying)
                CountDownButton(
                    text = stringResource(R.string.stop_exercise),
                    optionSelected = stop
                )
            else
                CountDownButton(
                    text = stringResource(R.string.start_exercise),
                    optionSelected = start
                )
        }

    }
}
