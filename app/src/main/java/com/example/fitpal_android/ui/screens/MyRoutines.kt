package com.example.fitpal_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpal_android.ui.SimulatedStore
import com.example.fitpal_android.ui.components.cards.RoutineCard

@Composable
fun MyRoutines(

) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SimulatedStore.myRoutines().size) {
                RoutineCard(
                    name = SimulatedStore.myRoutines()[it].name,
                    imageUrl = SimulatedStore.myRoutines()[it].imageUrl,
                    description = SimulatedStore.myRoutines()[it].description,
                    rating = SimulatedStore.myRoutines()[it].rating
                )
            }
        }
    }
}