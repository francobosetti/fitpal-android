package com.example.fitpal_android.ui.components.cards.detailed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import coil.compose.AsyncImage
import com.example.fitpal_android.R

@Composable
fun DetailedRoutineCard(
    name: String,
    description: String,
    difficulty: String,
    videoUrl: String,
    modifier: Modifier,
    rating: Double,
    userRating: Double?,
    isFavorite: Boolean,
    onStartPressedCallback: () -> Unit,
    onSharePressedCallback: () -> Unit,
    onFavoritePressedCallback: () -> Unit,
    onRatingSubmitCallback: (Double) -> Unit
) {
    var showPopup by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(userRating ?: 0.0) }

    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column {

            // Image
            AsyncImage(
                model = videoUrl,
                contentDescription = "Routine video",
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(175.dp)
                    .clip(
                        RoundedCornerShape(percent = 5)
                    ),
                contentScale = ContentScale.FillWidth
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
            ) {
                // Routine name
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onPrimary,
                )

                // Row with fav and share buttons
                Row {

                    // Share button
                    IconButton(
                        onClick = { onSharePressedCallback() },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share button",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }

                    // Fav button
                    IconButton(
                        onClick = { onFavoritePressedCallback() },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_bookmark_24),
                            contentDescription = "Fav button",
                            modifier = Modifier.size(28.dp),
                            tint = if (isFavorite) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }


            Row(
                modifier = Modifier.padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = rating.toString(),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary,
                )
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(15.dp)
                )
            }

            // Routine description
            Text(
                text = stringResource(R.string.description_title),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            )

            // difficulty of the routine
            Text(
                text = stringResource(R.string.difficulty_title),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 8.dp)
            )

            Text(
                text = difficulty.replaceFirstChar { it.uppercaseChar() },
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            )


            // Rating
            /*
            RatingRow(
                rating = rating,
                onRatingPressed = { showPopup = true },
                onStarPressed = { },
                starPressable = false
            )
             */

            // Rating text button
            TextButton(
                onClick = { showPopup = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.rating_button_text),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colors.primary,
                )
            }


            // Start routine button
            Button(
                onClick = { onStartPressedCallback() },
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                // Center text
                Text(text = stringResource(R.string.start_routine_button))
            }

            // Popup with rating options
            if (showPopup) {
                Popup(alignment = Alignment.Center, onDismissRequest = { showPopup = false }) {
                    Card(
                        backgroundColor = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Column {
                            // Title
                            Text(
                                text = stringResource(R.string.rating_title),
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.padding(12.dp)
                            )

                            // Rating options
                            RatingRow(
                                rating = selectedRating,
                                onRatingPressed = { onRatingSubmitCallback(selectedRating); showPopup = false },
                                onStarPressed = { rating -> selectedRating = rating },
                                starPressable = true
                            )

                            // Cancel button
                            Button(
                                onClick = { showPopup = false },
                                modifier = Modifier
                                    .padding(12.dp)
                            ) {
                                // Center text
                                Text(text = stringResource(R.string.cancel_button_text))
                            }

                        }
                    }
                }
            }
        }
    }
}

// TODO: hacer que maneje doubles
@Composable
fun RatingRow(
    rating: Double,
    onRatingPressed: (Double) -> Unit,
    onStarPressed: (Double) -> Unit,
    starPressable: Boolean
) {
    val starModifier = Modifier
        .padding(8.dp)
        .size(30.dp)

    // Routine rating
    // 5 stars (1 star = 1 rating)
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        // Star 1
        Icon(
            Icons.Filled.Star,
            contentDescription = "Star 1",
            tint = if (rating >= 1) MaterialTheme.colors.primary else MaterialTheme.colors.background,
            modifier = if (starPressable) starModifier.clickable { onStarPressed(1.0) } else starModifier
        )
        // Star 2
        Icon(
            Icons.Filled.Star,
            contentDescription = "Star 2",
            tint = if (rating >= 2) MaterialTheme.colors.primary else MaterialTheme.colors.background,
            modifier = if (starPressable) starModifier.clickable { onStarPressed(2.0) } else starModifier
        )
        // Star 3
        Icon(
            Icons.Filled.Star,
            contentDescription = "Star 3",
            tint = if (rating >= 3) MaterialTheme.colors.primary else MaterialTheme.colors.background,
            modifier = if (starPressable) starModifier.clickable { onStarPressed(3.0) } else starModifier
        )
        // Star 4
        Icon(
            Icons.Filled.Star,
            contentDescription = "Star 4",
            tint = if (rating >= 4) MaterialTheme.colors.primary else MaterialTheme.colors.background,
            modifier = if (starPressable) starModifier.clickable { onStarPressed(4.0) } else starModifier
        )
        // Star 5
        Icon(
            Icons.Filled.Star,
            contentDescription = "Star 5",
            tint = if (rating >= 5) MaterialTheme.colors.primary else MaterialTheme.colors.background,
            modifier = if (starPressable) starModifier.clickable { onStarPressed(5.0) } else starModifier
        )

        // Rating text button
        TextButton(
            onClick = { onRatingPressed(rating) },
            modifier = Modifier.padding(end = 8.dp, start = 4.dp, bottom = 8.dp, top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.rating_button_text),
                style = MaterialTheme.typography.body1,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colors.primary,
            )
        }
    }
}