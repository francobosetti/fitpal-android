package com.example.fitpal_android.ui.screens.appContent.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fitpal_android.util.SettingsManager

class SettingsViewModel(
    private val settingsManager: SettingsManager
) : ViewModel() {
    var settingsState by mutableStateOf(
        SettingsState(
            executionMode = settingsManager.getExecutionMode()
        )
    )
        private set

    fun toggleExecutionMode() {
        settingsManager.toggleExecutionMode()
        settingsState = settingsState.copy(
            executionMode = settingsManager.getExecutionMode()
        )
    }

    fun isDetailedMode(): Boolean {
        return settingsManager.isDetailedMode()
    }
}
