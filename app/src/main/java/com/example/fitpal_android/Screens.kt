package com.example.fitpal_android


sealed class Screens(val title: Int, val icon: Int, val iconDesc: String, val route: String) {

    // ---- Main Screens ----

    object Exercises : Screens(
        R.string.exercises_screen,
        R.drawable.ic_baseline_self_improvement_24,
        "exercise",
        "Exercises"
    )

    object Routines : Screens(
        R.string.my_routine_screen,
        R.drawable.ic_baseline_sports_gymnastics_24,
        "MyRoutine",
        "Routines"
    )

    object ExploreRoutines : Screens(
        R.string.explore_routines_screen,
        R.drawable.ic_baseline_explore_24,
        "Routines",
        "ExploreRoutines"
    )

    object FavoriteRoutine : Screens(
        R.string.fav_routine_screen,
        R.drawable.ic_baseline_star_24,
        "FavRoutines",
        "FavRoutines"
    )

    object Profile :
        Screens(R.string.profile_screen, R.drawable.ic_baseline_person_24, "Profile", "Profile")

    // TODO : make icons optional
    // ---- Secondary Screens ----
    object DetailedExercise : Screens(
        R.string.detailed_exercise_screen,
        R.drawable.ic_baseline_explore_24,
        "DetailedExercise",
        "DetailedExercise/{exerciseId}"
    )

    object DetailedRoutine : Screens(
        R.string.detailed_routine_screen,
        R.drawable.ic_baseline_explore_24,
        "DetailedRoutine",
        "DetailedRoutine/{routineId}"
    )

    object ExecRoutine : Screens(
        R.string.exec_routine_screen,
        R.drawable.ic_baseline_explore_24,
        "ExecRoutine",
        "ExecRoutine/{routineId}"
    )

    // ---- Auth Screens ----
    object LogIn :
        Screens(R.string.log_in_screen, R.drawable.ic_baseline_explore_24, "LogIn", "LogIn")

    object SignUp :
        Screens(R.string.sign_up_screen, R.drawable.ic_baseline_explore_24, "SignUp", "SignUp")

    object Verify :
        Screens(R.string.verify_screen, R.drawable.ic_baseline_explore_24, "Verify", "Verify/{email}/{password}")
}