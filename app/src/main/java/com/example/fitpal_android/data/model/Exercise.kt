package com.example.fitpal_android.data.model

class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val seconds : Long,
    val reps : Int
)