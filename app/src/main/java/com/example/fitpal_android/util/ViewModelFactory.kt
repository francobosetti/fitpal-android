package com.example.fitpal_android.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.fitpal_android.data.repository.UserRepository


class ViewModelFactory constructor(
    // Aca hay que poner todos los repositorios que se necesiten
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,

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
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}