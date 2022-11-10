package com.example.fitpal_android.ui.components.cards.detailed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitpal_android.R

@Composable
fun DetailedExerciseCard(name: String, description: String, tags: List<String>, videoUrl: String) {
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
                    .padding(12.dp)
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
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 8.dp)
            )

            // Exercise description
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

            // Essence of the exercise
            Text(
                text = stringResource(R.string.essence_exercise_title),
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

        }
    }
}
