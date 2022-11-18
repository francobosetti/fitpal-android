package com.example.fitpal_android.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// Card for displaying a routine
// It shows a routine image, the name of the routine, the routine description and the routine rating (stars)

@Composable
fun RoutineCard(name: String, difficulty: String, imageUrl: String, rating: Double, modifier: Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column {

            // Image
            AsyncImage(
                model = imageUrl,
                contentDescription = "Routine video",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(percent = 5)
                    ),
                contentScale = ContentScale.FillWidth
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Routine name
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(8.dp)
                )


                Row(
                    modifier = Modifier.padding(8.dp),
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
            }

            // Routine difficulty
            Text(
                text = difficulty.replaceFirstChar { it.uppercaseChar() },
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoutineCardPreview() {

}