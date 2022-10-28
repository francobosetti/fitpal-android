package com.example.fitpal_android.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// Card for displaying an exercise
// It shows a video thumbnail, the name of the exercise, the exercise tags and the exercise description

@Composable
fun ExerciseCard(name: String, description: String, tags: List<String>, videoUrl: String) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column {

            // Video thumbnail
            /* TODO : implementar video (otra vez xd)*/
            AsyncImage(
                model = videoUrl,
                contentDescription = "Exercise video",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(175.dp)
                    .clip(
                        RoundedCornerShape(percent = 5)
                    ),
                contentScale = ContentScale.FillWidth
            )

            // Exercise name
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(8.dp)
            )

            // Exercise tags
            Row {
                tags.forEach { tag ->
                    // Tag
                    BuildChip(label = tag)
                }
            }

            // Exercise description
            Text(
                text = "Description",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun BuildChip(label: String) {
    Box(modifier = Modifier.padding(start = 8.dp)) {
        Surface(
            elevation = 1.dp,
            shape = CircleShape,
            color = MaterialTheme.colors.primary,
        ) {
            Text(
                label,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}