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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitpal_android.ui.theme.FitpalandroidTheme

// Card for displaying a routine
// It shows a routine image, the name of the routine, the routine description and the routine rating (stars)

@Composable
fun RoutineCard(name: String, description: String, imageUrl: String, rating: Double) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column {

            // Image
            AsyncImage(
                model = imageUrl,
                contentDescription = "Routine video",
                modifier = Modifier
                    .padding(8.dp)
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
                modifier = Modifier.padding(8.dp)
            )

            // Routine description
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

            // Routine rating
            // 5 stars (1 star = 1 rating)
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
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
        }
    }
}

@Preview
@Composable
fun RoutineCardPreview() {
    FitpalandroidTheme {
        RoutineCard(
            name = "Routine name",
            description = "Routine description",
            imageUrl = "https://images.unsplash.com/photo-1616166330004-8b8b2b2b2b1d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
            rating = 4.5
        )
    }

}