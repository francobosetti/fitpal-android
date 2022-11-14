package com.example.fitpal_android.data.model


class Cycle(

    var id: Int,
    var name: String,
    var type: String,
    var order: Int,
    var repetitions: Int,
    var Rest: Int,
    var exercises: List<CycleExercise>,
)