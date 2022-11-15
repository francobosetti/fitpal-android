package com.example.fitpal_android.data.model

import com.example.fitpal_android.data.repository.DEPRECATED.Exercise

class Routine(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    var rating: Double,
    val difficulty: String,
    val cycles: List<Cycle>,
    val isFavorite: Boolean,
)