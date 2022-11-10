package com.example.fitpal_android.ui.components.cards.detailed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
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
fun DetailedRoutineCard(
    name: String,
    description: String,
    tags: List<String>,
    videoUrl: String,
    modifier: Modifier,
    rating: Double,
    onStartPressedCallback: () -> Unit,
    onSharePressedCallback: () -> Unit
) {
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
                    .padding(bottom = 8.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
            ) {
                // Routine name
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onPrimary,
                )

                // Share button
                IconButton(
                    onClick = { onSharePressedCallback() },
                ) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share button", modifier = Modifier.size(28.dp), tint = MaterialTheme.colors.primary)
                }
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

            // Essence of the routine
            Text(
                text = stringResource(R.string.essence_routine_title),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 8.dp)
            )

            // Shows tags in a checklist
            Column {
                tags.forEach { tag ->
                    // Tag
                    Row {
                        // Check icon
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Check icon",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                        )

                        // Tag text
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                        )
                    }
                }
            }

            // Routine rating
            // 5 stars (1 star = 1 rating)
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 8.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                // Star 1
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star 1",
                    tint = if (rating >= 1) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
                // Star 2
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star 2",
                    tint = if (rating >= 2) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
                // Star 3
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star 3",
                    tint = if (rating >= 3) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
                // Star 4
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star 4",
                    tint = if (rating >= 4) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
                // Star 5
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star 5",
                    tint = if (rating >= 5) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
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
        }
    }
}