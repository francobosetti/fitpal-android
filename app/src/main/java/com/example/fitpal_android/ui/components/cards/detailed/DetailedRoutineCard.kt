package com.example.fitpal_android.ui.components.cards.detailed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailedRoutineCard(
    name: String,
    description: String,
    tags: List<String>,
    videoUrl: String,
    modifier: Modifier,
    rating: Double,
    onStartPressedCallback: () -> Unit
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

            // Routine name
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 8.dp)
            )

            // Routine description
            Text(
                text = "Description",
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
                text = "Essence of routine",
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
            Button(onClick = { onStartPressedCallback() },
                modifier = Modifier.padding(12.dp).fillMaxWidth()) {
                // Center text
                Text(text = "Start Routine")
            }
        }
    }
}