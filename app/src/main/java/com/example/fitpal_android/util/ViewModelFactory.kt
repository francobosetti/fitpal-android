package com.example.fitpal_android.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.fitpal_android.MainActivityViewModel
import com.example.fitpal_android.data.repository.ExerciseRepository
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.ui.screens.appContent.detailedExercise.DetailedExerciseViewModel
import com.example.fitpal_android.ui.screens.appContent.detailedRoutine.DetailedRoutineViewModel
import com.example.fitpal_android.ui.screens.appContent.exercises.ExercisesViewModel
import com.example.fitpal_android.ui.screens.appContent.exploreRoutines.ExploreRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.favRoutines.FavRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.mainScreen.MainScreenViewModel
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutinesViewModel
import com.example.fitpal_android.ui.screens.appContent.profile.ProfileViewModel
import com.example.fitpal_android.ui.screens.authentication.login.LoginViewModel
import com.example.fitpal_android.ui.screens.authentication.signup.SignUpViewModel
import com.example.fitpal_android.ui.screens.authentication.verify.VerifyViewModel


class ViewModelFactory constructor(
    // Aca hay que poner todos los repositorios que se necesiten
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository,

    // Ids para los viewmodels que necesiten
    private val id: Int? = null,

    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        // Aca hay que poner todos los viewmodels que se necesiten, y lo construimos a partir de los parametros que necesite
        when {
            // Asi: isAssignableFrom(MyViewModel::class.java) -> MyViewModel( ... parametros que necesite ...)

            isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(userRepository =  userRepository)

            // Authentication
            isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(userRepository =  userRepository)
            isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(userRepository =  userRepository)
            isAssignableFrom(VerifyViewModel::class.java) -> VerifyViewModel(userRepository =  userRepository)

            // AppContent
            isAssignableFrom(MainScreenViewModel::class.java) -> MainScreenViewModel(userRepository =  userRepository)
            isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(userRepository =  userRepository)
            isAssignableFrom(ExercisesViewModel::class.java) -> ExercisesViewModel(exerciseRepository =  exerciseRepository)
            isAssignableFrom(DetailedExerciseViewModel::class.java) -> DetailedExerciseViewModel(exerciseRepository = exerciseRepository, exerciseId = id!!)
            isAssignableFrom(ExploreRoutinesViewModel::class.java) -> ExploreRoutinesViewModel(routineRepository =  routineRepository)
            isAssignableFrom(MyRoutinesViewModel::class.java) -> MyRoutinesViewModel(routineRepository =  routineRepository)
            isAssignableFrom(FavRoutinesViewModel::class.java) -> FavRoutinesViewModel(routineRepository =  routineRepository)
            isAssignableFrom(DetailedRoutineViewModel::class.java) -> DetailedRoutineViewModel(routineRepository =  routineRepository, id!!)

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}